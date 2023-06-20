package com.lungvision.lungdiseasexrayclassificationbe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "file")
@Table(name = "medical_scan")
public class MedicalScan {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false, insertable = false)
    private String id;

    @OneToOne
    @JoinColumn(name = "prediction_id")
    private Prediction prediction;

    private String fileId;

    private LocalDateTime scanDate;

    private String fileName;

    @Enumerated(EnumType.STRING)
    private FileType fileType;
    public MedicalScan(String fileId, String fileName, FileType fileType) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
    }
}
