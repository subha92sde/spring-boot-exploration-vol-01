package com.spring.boot.exploration.applicant;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantLoginReqDTO {
    @NotNull(message = "SSN is required")
    @Pattern(regexp = "\\d{3}-\\d{2}-\\d{4}", message = "Invalid SSN format. Expected format: XXX-XX-XXXX")
    @Size(min = 11, max = 11, message = "SSN must be exactly 11 characters long.")
    @JsonProperty
    private String ssn;

    @NotNull(message = "Password is required")
    @Pattern(regexp = "^pass-\\d{3}-\\d{2}-\\d{4}$", message = "Password must start with 'pass-' followed by the SSN (e.g., pass-XXX-XX-XXXX)")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
