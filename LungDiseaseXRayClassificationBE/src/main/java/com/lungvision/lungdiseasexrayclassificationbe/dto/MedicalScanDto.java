package com.lungvision.lungdiseasexrayclassificationbe.dto;

import com.lungvision.lungdiseasexrayclassificationbe.model.FileType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicalScanDto {
    private String file;
    private String fileName;
    private FileType fileType;
}