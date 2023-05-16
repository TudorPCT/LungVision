package com.lungvision.lungdiseasexrayclassificationbe.controller;

import com.lungvision.lungdiseasexrayclassificationbe.dto.MedicalScanDto;
import com.lungvision.lungdiseasexrayclassificationbe.service.prediction.PredictionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prediction")
@RequiredArgsConstructor
public class PredictionController {
    private final PredictionServiceImpl predictionService;

    @GetMapping
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Prediction controller works!", HttpStatus.OK);
    }

    @PostMapping("/predict")
    public ResponseEntity<String> predict(@RequestBody MedicalScanDto medicalScanDto) {
        if (medicalScanDto.getFile() == null || medicalScanDto.getFileType() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String prediction = predictionService.predict(medicalScanDto);

        if(prediction == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(prediction, HttpStatus.OK);
    }
}
