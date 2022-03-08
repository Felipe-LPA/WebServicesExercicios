package com.aula04.banco.banco.dto;

import com.aula04.banco.banco.utils.saldo.ValorPositivo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
public class RequestDeposito {
    @ValorPositivo
    private Double valor;
    private UUID conta;
}