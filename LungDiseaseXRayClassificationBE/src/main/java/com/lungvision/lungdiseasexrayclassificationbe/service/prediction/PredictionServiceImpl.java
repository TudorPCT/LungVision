package com.lungvision.lungdiseasexrayclassificationbe.service.prediction;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lungvision.lungdiseasexrayclassificationbe.dto.MedicalScanDto;
import com.lungvision.lungdiseasexrayclassificationbe.dto.PredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.dto.SavePredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.dto.SavedPredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.exception.CloudinaryException;
import com.lungvision.lungdiseasexrayclassificationbe.exception.PredictionNotFoundException;
import com.lungvision.lungdiseasexrayclassificationbe.exception.ServiceUnavailable;
import com.lungvision.lungdiseasexrayclassificationbe.exception.UserNotFoundException;
import com.lungvision.lungdiseasexrayclassificationbe.mapper.PredictionMapper;
import com.lungvision.lungdiseasexrayclassificationbe.model.LungDisease;
import com.lungvision.lungdiseasexrayclassificationbe.model.MedicalScan;
import com.lungvision.lungdiseasexrayclassificationbe.model.Prediction;
import com.lungvision.lungdiseasexrayclassificationbe.model.User;
import com.lungvision.lungdiseasexrayclassificationbe.repository.PredictionRepository;
import com.lungvision.lungdiseasexrayclassificationbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PredictionServiceImpl implements PredictionService{
    private final Cloudinary cloudinary;
    private final MessageSource messageSource;
    private final PredictionRepository predictionRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    @Value("${prediction.api.base.url}" + "/predict")
    private String predictionApiUrl;
    @Value("${cloudinary.image.url}")
    private String cloudinaryImageUrl;

    private final ObjectMapper objectMapper;

    static DecimalFormat decimalFormat = new DecimalFormat("#." + "0".repeat(2));

    @Override
    public PredictionDto predict(MedicalScanDto medicalScanDto) {

        Locale locale = LocaleContextHolder.getLocale();
        HttpEntity<byte[]> request;
        HttpHeaders headers = new HttpHeaders();
        PredictionDto predictionDto = new PredictionDto();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        request = new HttpEntity<>(Base64.getDecoder().decode(medicalScanDto.getFile()), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(predictionApiUrl, request , String.class);

        if(response.getStatusCode().isError())
            throw new ServiceUnavailable(messageSource.getMessage("prediction.error.service.unavailable", null, locale));

        try {

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            String prediction = jsonNode.get("prediction").asText();
            String probabilities = jsonNode.get("probability").asText();

            predictionDto.setPrediction(LungDisease.valueOf(prediction.toUpperCase()));

            for (String probability : probabilities.substring(1, probabilities.length() - 1).split(" ")) {
                predictionDto.getProbabilities().add(Double.parseDouble(decimalFormat.format(Double.parseDouble(probability) * 100)));
            }

        } catch (Exception e) {
            throw new ServiceUnavailable(messageSource.getMessage("prediction.error.service.unavailable", null, locale));
        }

        return predictionDto;
    }

    @Override
    public String save(SavePredictionDto savePredictionDto, String userId) throws RuntimeException {
        Locale locale = LocaleContextHolder.getLocale();

        MedicalScanDto medicalScanDto = new MedicalScanDto(
                savePredictionDto.getFile(),
                savePredictionDto.getFileName(),
                savePredictionDto.getFileType()
        );

        PredictionDto predictionDto = predict(medicalScanDto);

        User owner = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(messageSource.getMessage("user.error.not.found", null, locale)));

        Map uploadResult;
        try {
            uploadResult = cloudinary.uploader().upload(Base64.getDecoder().decode(medicalScanDto.getFile()), ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new CloudinaryException(messageSource.getMessage("prediction.error.cloudinary.save", null, locale));
        }

        String publicId = uploadResult.get("public_id").toString();

        Prediction prediction = new Prediction(
                owner,
                predictionDto.getPrediction(),
                savePredictionDto.getPatientFirstName(),
                savePredictionDto.getPatientLastName(),
                savePredictionDto.getPatientAge(),
                savePredictionDto.getPatientGender(),
                savePredictionDto.getScanDate(),
                publicId,
                savePredictionDto.getFileName(),
                savePredictionDto.getFileType()
        );

        predictionRepository.save(prediction);

        return messageSource.getMessage("prediction.success.save", null, locale);
    }

    @Override
    public List<SavedPredictionDto> getSavedPredictions(String userId) {
        List<Prediction> predictionList = predictionRepository.findAllByOwnerId(userId);
        List<SavedPredictionDto> savedPredictionDtoList = new ArrayList<>();

        predictionList.forEach(prediction -> savedPredictionDtoList.add(
                PredictionMapper.predictionToSavedPredictionDto(prediction, cloudinaryImageUrl)
        ));

        return savedPredictionDtoList;
    }

    @Override
    public String updateDetails(SavedPredictionDto savedPredictionDto, String userId) {
        Locale locale = LocaleContextHolder.getLocale();
        User owner = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(messageSource.getMessage("user.error.not.found", null, locale)));

        Prediction prediction = predictionRepository.findById(savedPredictionDto.getId()).orElseThrow(() -> new PredictionNotFoundException(messageSource.getMessage("prediction.error.not.found", null, locale)));

        if (!prediction.getOwner().getId().equals(owner.getId())) throw new PredictionNotFoundException(messageSource.getMessage("prediction.error.not.found", null, locale));

        prediction.setPatientFirstName(savedPredictionDto.getPatientFirstName());
        prediction.setPatientLastName(savedPredictionDto.getPatientLastName());
        prediction.setPatientAge(savedPredictionDto.getPatientAge());
        prediction.setPatientGender(savedPredictionDto.getPatientGender());
        prediction.setScanDate(savedPredictionDto.getScanDate());

        predictionRepository.save(prediction);

        return messageSource.getMessage("prediction.success.update", null, locale);
    }

    @Override
    public String delete(String id, String userId) {
        Locale locale = LocaleContextHolder.getLocale();
        predictionRepository.deleteByIdAndOwnerId(id, userId);

        return messageSource.getMessage("prediction.success.delete", null, locale);
    }

    @Override
    public MedicalScanDto getPredictionById(String userId, String id) {
        MedicalScan medicalScan = predictionRepository.findMedicalScanByIdAndOwnerId(id, userId);

        return new MedicalScanDto(
                cloudinaryImageUrl.concat(medicalScan.getFileId()),
                medicalScan.getFileName(),
                medicalScan.getFileType());
    }
}
