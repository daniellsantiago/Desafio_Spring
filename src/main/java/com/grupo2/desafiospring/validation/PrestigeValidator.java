package com.grupo2.desafiospring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PrestigeValidator implements ConstraintValidator<PrestigeValidation, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            return Arrays.asList(new String[]{"*","**","***", "****", "*****"}).contains(s);
        }
        return true;
    }
}
