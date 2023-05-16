package com.lungvision.lungdiseasexrayclassificationbe.service.prediction;

import com.lungvision.lungdiseasexrayclassificationbe.dto.MedicalScanDto;
import com.lungvision.lungdiseasexrayclassificationbe.model.LungDisease;
import org.springframework.stereotype.Service;

public interface PredictionService {
    String predict(MedicalScanDto medicalScanDto);
}
