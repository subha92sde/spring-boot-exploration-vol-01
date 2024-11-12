package com.spring.boot.exploration.movie.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Year;

@Getter
@Setter
public class MovieReqDTO {
    @NotNull
    @Size(min = 3, max = 15)
    private String title;

    @NotNull
    @DecimalMax(value = "9.9")
    @DecimalMin(value = "4.0")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal imdbRating;

    @NotNull
    @ValidYear
    private Year yearOfRelease;
}
