package com.aula04.banco.banco.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Conta {
    private Integer numeroConta;
    private Integer agencia;
    private TipoConta tipoConta;
}
