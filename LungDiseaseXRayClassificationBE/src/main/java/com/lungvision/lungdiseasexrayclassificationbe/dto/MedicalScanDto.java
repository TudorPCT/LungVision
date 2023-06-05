package com.lungvision.lungdiseasexrayclassificationbe.dto;

import com.lungvision.lungdiseasexrayclassificationbe.model.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MedicalScanDto {
    @NotBlank
    private String file;
    @NotBlank
    private String fileName;
    @NotNull
    private FileType fileType;
}