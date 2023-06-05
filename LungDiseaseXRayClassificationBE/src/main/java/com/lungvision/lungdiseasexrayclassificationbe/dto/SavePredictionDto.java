package com.lungvision.lungdiseasexrayclassificationbe.dto;

import com.lungvision.lungdiseasexrayclassificationbe.model.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SavePredictionDto {
    @NotBlank
    private String patientFirstName;
    @NotBlank
    private String patientLastName;
    @NotNull
    private Integer patientAge;
    @NotBlank
    private String patientGender;
    @NotNull
    private LocalDateTime scanDate;
    @NotBlank
    private String file;
    @NotBlank
    private String fileName;
    @NotNull
    private FileType fileType;
}
