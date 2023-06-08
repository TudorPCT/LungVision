package com.lungvision.lungdiseasexrayclassificationbe.dto;

import com.lungvision.lungdiseasexrayclassificationbe.model.LungDisease;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SavedPredictionDto {
    @NotBlank
    private String id;
    @NotBlank
    private String patientFirstName;
    @NotBlank
    private String patientLastName;
    @NotNull
    private Integer patientAge;
    @NotBlank
    private String patientGender;
    @NotNull LungDisease lungDisease;
    @NotNull
    private LocalDateTime scanDate;
    @NotNull
    private String medicalScanUrl;
}
