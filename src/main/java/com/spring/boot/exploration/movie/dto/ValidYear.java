package com.spring.boot.exploration.movie.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = YearValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidYear {
    String message() default "invalid";

    // Additional attributes for specific error messages
    String invalidYearLengthMessage() default "must be a 4-digit number";

    String rangeMessage() default "must be between 1900 and the current year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
