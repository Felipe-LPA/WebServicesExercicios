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
    Random random = new Random();
    @Test
    void VerificaValorDepositado() throws Exception {
//        RequestCliente requestCliente = new RequestCliente(
//                "Felipe",
//                "Felipe@email.com",
//                "asdasd");
        Conta conta = new Conta(UUID.randomUUID(), random.nextInt(), random.nextInt(), TipoConta.CONTA_CORRENTE, 0.0);
//        List<Conta> contas = new ArrayList<>();
//        contas.add(conta);
//        Cliente cliente = new Cliente(UUID.randomUUID(),
//                requestCliente.getNome(),
//                requestCliente.getEmail(),
//                requestCliente.getSenha(),
//                contas);
        RequestDeposito requestDeposito = new RequestDeposito(
            23.5,
                conta.getId());
        double valorAnterior = conta.getSaldo();
        operacoesService.deposita(conta.getId(), requestDeposito);
        Assertions.assertTrue(conta.getSaldo() == valorAnterior + requestDeposito.getValor());
//        Assertions.assertThrows(Exception.class, ()-> operacoesService.deposita(UUID.randomUUID(), requestDeposito));
    }
}
