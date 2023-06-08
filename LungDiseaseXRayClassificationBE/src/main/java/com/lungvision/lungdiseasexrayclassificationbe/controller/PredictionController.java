package com.lungvision.lungdiseasexrayclassificationbe.controller;

import com.lungvision.lungdiseasexrayclassificationbe.dto.MedicalScanDto;
import com.lungvision.lungdiseasexrayclassificationbe.dto.PredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.dto.SavePredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.dto.SavedPredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.service.prediction.PredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prediction")
@RequiredArgsConstructor
public class PredictionController {
    private final PredictionService predictionService;

    @PostMapping("/predict")
    public ResponseEntity<PredictionDto> predict(@Valid @RequestBody MedicalScanDto medicalScanDto) {
        PredictionDto prediction = predictionService.predict(medicalScanDto);

        if(prediction == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(prediction, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody SavePredictionDto savePredictionDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        String response = predictionService.save(savePredictionDto, userId);

        return new ResponseEntity<>("{\"message\": \"" + response + "\"}", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SavedPredictionDto>> getSavedPredictions(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        List<SavedPredictionDto> savedPredictionDtoList = predictionService.getSavedPredictions(userId);

        if(savedPredictionDtoList == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(savedPredictionDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalScanDto> getMedicalScan(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        MedicalScanDto medicalScanDto = predictionService.getPredictionById(userId, id);

        if(medicalScanDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(medicalScanDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updatePredictionDetails(@Valid @RequestBody SavedPredictionDto savedPredictionDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        String response = predictionService.updateDetails(savedPredictionDto, userId);

        return new ResponseEntity<>("{\"message\": \"" + response + "\"}", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSavedPrediction(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        if(id == null)
            return new ResponseEntity<>("Invalid input!", HttpStatus.BAD_REQUEST);

        String response = predictionService.delete(id, userId);

        return new ResponseEntity<>("{\"message\": \"" + response + "\"}", HttpStatus.OK);
    }


}
