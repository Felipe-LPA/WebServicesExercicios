package com.aula04.banco.banco.utils.saldo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorPositivoValidador implements ConstraintValidator<ValorPositivo, Double> {
    @Override
    public void initialize(ValorPositivo constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if(value < 0){
        return false;
        }
        return true;
    }
}
