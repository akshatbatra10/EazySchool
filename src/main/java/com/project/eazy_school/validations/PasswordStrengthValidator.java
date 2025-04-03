package com.project.eazy_school.validations;

import com.project.eazy_school.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    Set<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords = new HashSet<>(Arrays.asList("12345", "password", "qwertyt"));
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext constraintValidatorContext) {
        return passwordField != null && !weakPasswords.contains(passwordField);
    }
}
