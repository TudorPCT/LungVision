package com.lungvision.lungdiseasexrayclassificationbe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NewUserDto {
    @NotBlank
    String email;
    @NotBlank
    String password;
}
