package com.lungvision.lungdiseasexrayclassificationbe.dto;

import com.lungvision.lungdiseasexrayclassificationbe.model.LungDisease;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PredictionDto {
    @NotNull
    LungDisease prediction;
    @NotEmpty
    List<Double> probabilities = new ArrayList<>();
}
