package com.aula04.banco.banco.service;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestOperacoes;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperacoesService {

    ClienteService clienteService = new ClienteService();

    BancoCliente bancoCliente = BancoAula04Application.bancoCliente;

    public Conta deposita(UUID id, RequestOperacoes requestOperacoes) throws Exception {
        Cliente cliente = clienteService.detalhesCliente(id);
        Optional<Conta> conta = bancoCliente.getConta(cliente, requestOperacoes.getConta());
        if(conta.isPresent()) {
            Double novoSaldo = conta.get().getSaldo() + requestOperacoes.getValor();
            conta.get().setSaldo(novoSaldo);
            return bancoCliente.atualizaSaldo(cliente, conta.get());
        } else {
                throw new Exception("Conta não encontrada");
        }

    }
    public Conta sacar(UUID id, RequestOperacoes requestOperacoes) throws Exception {
        Cliente cliente = clienteService.detalhesCliente(id);
        Optional<Conta> conta = bancoCliente.getConta(cliente, requestOperacoes.getConta());
        if(conta.isPresent()) {
            if(conta.get().getSaldo() >= requestOperacoes.getValor()){
            Double novoSaldo = conta.get().getSaldo() - requestOperacoes.getValor();
            conta.get().setSaldo(novoSaldo);
            return bancoCliente.atualizaSaldo(cliente, conta.get());
            }
            else {
                throw new Exception("Valor inválido");
            }
        } else {
            throw new Exception("Conta não encontrada");
        }
    }
}
