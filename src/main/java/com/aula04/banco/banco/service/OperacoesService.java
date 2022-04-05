package com.aula04.banco.banco.service;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperacoesService {

    ClienteService clienteService = new ClienteService();

    BancoCliente bancoCliente = BancoAula04Application.bancoCliente;

    public Conta deposita(UUID id, RequestDeposito requestDeposito) throws Exception {
        Cliente cliente = clienteService.detalhesCliente(id);
        Optional<Conta> conta = bancoCliente.getConta(cliente, requestDeposito.getConta());
        if(conta.isPresent()) {
            Double novoSaldo = conta.get().getSaldo() + requestDeposito.getValor();
            conta.get().setSaldo(novoSaldo);
            Conta resultConta = bancoCliente.deposita(cliente, conta.get());
            return resultConta;
        } else {
                throw new Exception("Conta não encontrada");
        }


//        BancoAula04Application.bancoCliente.deposita(id, requestDeposito);
    }
}
