package com.lungvision.lungdiseasexrayclassificationbe.mapper;

import com.lungvision.lungdiseasexrayclassificationbe.dto.SavedPredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.model.Prediction;

public class PredictionMapper {
    public static SavedPredictionDto predictionToSavedPredictionDto(Prediction prediction, String imageServiceUrl) {
        return SavedPredictionDto.builder()
                .id(prediction.getId())
                .patientFirstName(prediction.getPatientFirstName())
                .patientLastName(prediction.getPatientLastName())
                .patientAge(prediction.getPatientAge())
                .patientGender(prediction.getPatientGender())
                .lungDisease(prediction.getLungDisease())
                .scanDate(prediction.getMedicalScan().getScanDate())
                .medicalScanUrl(imageServiceUrl.concat(prediction.getMedicalScan().getFileId()))
                .build();
    }
}
