package com.aula04.banco.banco.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    private UUID id;
    private Integer conta;
    private Integer agencia;
    private TipoConta tipoConta;
    private Double saldo;
}
