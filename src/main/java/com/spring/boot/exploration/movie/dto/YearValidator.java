package com.spring.boot.exploration.movie.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearValidator implements ConstraintValidator<ValidYear, Year> {
    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = Year.now().getValue();

    private String invalidYearLengthMessage;
    private String rangeMessage;

    @Override
    public void initialize(ValidYear constraintAnnotation) {
        this.invalidYearLengthMessage = constraintAnnotation.invalidYearLengthMessage();
        this.rangeMessage = constraintAnnotation.rangeMessage();
    }

    @Override
    public boolean isValid(Year value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        int year = value.getValue();

        // Check if year is 4 digits
        if (!isFourDigitYear(year)) {
            return buildViolation(context, invalidYearLengthMessage);
        }

        // Check if year is within range
        if (!isYearInRange(year)) {
            return buildViolation(context, rangeMessage);
        }

        return true;
    }

    private boolean isFourDigitYear(int year) {
        return String.valueOf(year).length() == 4;
    }

    private boolean isYearInRange(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    private boolean buildViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }
}
