package com.spring.boot.exploration.applicant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantLoginReqDTO {
    @NotNull
    @Pattern(regexp = "\\d{3}-\\d{2}-\\d{4}", message = "Invalid SSN format")
    @Size(min = 11, max = 11, message = "SSN must be exactly 11 characters long.")
    @JsonProperty
    private String ssn;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{5,10}$", message = "Password must be 5-10 characters long and contain only letters and digits.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
