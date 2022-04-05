package com.aula04.banco.banco.service;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestOperacoes;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

public class OperacoesServiceTest {
    OperacoesService operacoesService = new OperacoesService();
    ClienteService clienteService = new ClienteService();

    RequestCliente requestCliente = new RequestCliente(
            "Felipe",
            "Felipe@gmail",
            "123456"
    );
    Cliente cliente = clienteService.cadastraCliente(requestCliente);

    Random random = new Random();
    @Test
    void VerificaValorDepositado() throws Exception {

        Conta conta = cliente.getContas().get(0);
        RequestOperacoes requestDeposito = new RequestOperacoes(
            23.5,
                conta.getId());
        double valorAnterior = conta.getSaldo();
        Conta resultConta = operacoesService.deposita(cliente.getId(), requestDeposito);
        Assertions.assertTrue(resultConta.getSaldo() == valorAnterior + requestDeposito.getValor());
//
    }
    @Test
    void TentativaDeDepositoEmContaInexistente(){
        RequestOperacoes requestOperacoes = new RequestOperacoes(
                23.5,
                UUID.randomUUID());

        Assertions.assertThrows(Exception.class, () -> operacoesService.deposita(cliente.getId(), requestOperacoes));
    }

    @Test
    void TentativaDeSaqueComValorMaiorQueOAtualDaConta(){
        Conta conta = cliente.getContas().get(0);
        RequestOperacoes requestOperacoes = new RequestOperacoes(
                45.5,
                conta.getId());

        Assertions.assertThrows(Exception.class, () -> operacoesService.sacar(cliente.getId(), requestOperacoes));
    }

}
