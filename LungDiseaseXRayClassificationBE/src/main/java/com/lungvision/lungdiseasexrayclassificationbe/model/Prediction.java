package com.lungvision.lungdiseasexrayclassificationbe.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "prediction")
public class Prediction {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false, insertable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User owner;

    @Enumerated(EnumType.STRING)
    private LungDisease lungDisease;

    private String patientFirstName;
    private String patientLastName;

    private Integer patientAge;
    private String patientGender;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "prediction", cascade = CascadeType.ALL)

    private MedicalScan medicalScan;

    @Version
    private int version;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    private LocalDateTime updatedAt;

    public Prediction(User owner, LungDisease lungDisease, String patientFirstName, String patientLastName, Integer patientAge, String patientGender, LocalDateTime scanDate, String fileId, String fileName, FileType fileType) {
        this.owner = owner;
        this.lungDisease = lungDisease;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.medicalScan = new MedicalScan(null, this, fileId, scanDate, fileName, fileType);
    }

}
