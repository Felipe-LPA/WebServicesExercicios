package com.aula04.banco.banco.utils.senha;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SenhaValidador implements ConstraintValidator<Senha, String> {

    @Override
    public void initialize(Senha constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String senha, ConstraintValidatorContext context) {

        if(senha == null ||
                senha.length() <= 3 ||
                senha.length() >= 13 ||
                !temCaractereEspecial(senha)){return  false;}
        return true;
    }
    public boolean temCaractereEspecial(String senha){
        String[] digitos = senha.split("");
        for (String digito: digitos) {
            if(digito.contains("!@#$%&*()")) {
                return true;
            }
        }
        return false;
    }
}