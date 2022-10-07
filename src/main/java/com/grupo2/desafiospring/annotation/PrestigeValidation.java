package com.grupo2.desafiospring.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PrestigeValidator.class)
public @interface PrestigeValidation {
    String message() default "Formato inválido: prestige precisa ser uma sequência de '*'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
