package com.spring.boot.exploration.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantLoginReqDTO {
    @NotNull
    @Column(name = "ssn", unique = true, nullable = false, length = 11)
    private String ssn;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;
}
