package com.lungvision.lungdiseasexrayclassificationbe.service.prediction;

import com.lungvision.lungdiseasexrayclassificationbe.dto.MedicalScanDto;
import com.lungvision.lungdiseasexrayclassificationbe.dto.PredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.dto.SavePredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.dto.SavedPredictionDto;

import java.util.List;

public interface PredictionService {
    PredictionDto predict(MedicalScanDto medicalScanDto);
    String save(SavePredictionDto savePredictionDto, String userId);

    List<SavedPredictionDto> getSavedPredictions(String userId);

    String updateDetails(SavedPredictionDto savedPredictionDto, String userId);

    String delete(String id, String userId);

    MedicalScanDto getPredictionById(String userId, String id);
}
