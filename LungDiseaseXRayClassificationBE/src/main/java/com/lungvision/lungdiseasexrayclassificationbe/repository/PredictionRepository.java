package com.lungvision.lungdiseasexrayclassificationbe.repository;

import com.lungvision.lungdiseasexrayclassificationbe.dto.SavedPredictionDto;
import com.lungvision.lungdiseasexrayclassificationbe.model.MedicalScan;
import com.lungvision.lungdiseasexrayclassificationbe.model.Prediction;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PredictionRepository extends JpaRepository<Prediction, String> {
    List<Prediction> findAllByOwnerId(String userId);

    @Query("SELECT new com.lungvision.lungdiseasexrayclassificationbe.model.MedicalScan(" +
            "p.medicalScan.fileId, p.medicalScan.fileName, p.medicalScan.fileType) " +
            "FROM Prediction p WHERE p.id=?1 AND p.owner.id = ?2")
    MedicalScan findMedicalScanByIdAndOwnerId(String id, String userId);

    @Transactional
    @Modifying
    void deleteByIdAndOwnerId(@NonNull String id, @NonNull String ownerId);
}