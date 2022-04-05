package com.aula04.banco.banco.service;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class OperacoesServiceTest {
    OperacoesService operacoesService = new OperacoesService();
    ClienteService clienteService = new ClienteService();

    Random random = new Random();
    @Test
    void VerificaValorDepositado() throws Exception {
        RequestCliente requestCliente = new RequestCliente(
                "Felipe",
                "Felipe@gmail",
                "123456"
        );
        Cliente cliente = clienteService.cadastraCliente(requestCliente);
        Conta conta = cliente.getContas().get(0);
        RequestDeposito requestDeposito = new RequestDeposito(
            23.5,
                conta.getId());
        double valorAnterior = conta.getSaldo();
        Conta resultConta = operacoesService.deposita(cliente.getId(), requestDeposito);
        Assertions.assertTrue(resultConta.getSaldo() == valorAnterior + requestDeposito.getValor());
//
    }
}
