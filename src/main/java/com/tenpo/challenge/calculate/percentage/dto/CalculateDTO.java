package com.tenpo.challenge.calculate.percentage.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculateDTO {

    @NotNull(message = "num1 cannot be null")
    private Double num1;

    @NotNull(message = "num2 cannot be null")
    private Double num2;

}
