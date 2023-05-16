package com.lungvision.lungdiseasexrayclassificationbe.service.prediction;

import com.lungvision.lungdiseasexrayclassificationbe.dto.MedicalScanDto;
import com.lungvision.lungdiseasexrayclassificationbe.model.LungDisease;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class PredictionServiceImpl implements PredictionService{
    private final RestTemplate restTemplate;
    @Value("${prediction.api.base.url}" + "/predict")
    private String predictionApiUrl;

    public String predict(MedicalScanDto medicalScanDto) {

        HttpEntity<byte[]> request;
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        request = new HttpEntity<>(Base64.getDecoder().decode(medicalScanDto.getFile()), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(predictionApiUrl, request , String.class);

        return response.getBody();
    }
}
