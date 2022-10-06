package com.grupo2.desafiospring.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PrestigeValidator implements ConstraintValidator<PrestigeValidation, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.asList(new String[]{"*","**","***", "****", "*****"}).contains(s);
    }
}
