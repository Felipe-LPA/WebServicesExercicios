package com.aula04.banco.banco.model;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;

import java.util.*;

public class BancoCliente {
    private static List<Cliente> clientes = new ArrayList<>();
    public void adiciona(Cliente cliente){
        BancoCliente.clientes.add(cliente);
    }

    public List<Cliente> buscaClientes(){
        return BancoCliente.clientes;
    }

    public Cliente detalhesCliente(UUID id) throws Exception{
        Optional<Cliente> resultCliente = clientes.stream().filter(cliente -> Objects.equals(cliente.getId(), id)).findAny();
        if(resultCliente.isPresent()){
        return resultCliente.get();
        }
       throw new Exception("Usuário não encontrádo");
    }

    public Cliente atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception {
        clientes.stream().filter(cliente -> Objects.equals(cliente.getId(), id)).forEach(cliente -> {
            cliente.setNome(requestCliente.getNome());
            cliente.setEmail(requestCliente.getEmail());
            cliente.setSenha(requestCliente.getSenha());
        });
        return detalhesCliente(id);
    }

    public void removeCliente(UUID id) {
        Optional<Cliente> resultCliente = clientes.stream().filter(cliente -> Objects.equals(cliente.getId(), id)).findAny();
        if(resultCliente.isPresent()){
            clientes.remove(resultCliente.get());
        }
    }
    public void deposita(UUID id, RequestDeposito requestDeposito) {
        BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id))
                .forEach(cliente -> {
                    Optional<Conta> resultConta = cliente.getContas().stream().filter(conta -> Objects.equals(conta.getId(),requestDeposito.getConta())).findAny();
                    if(resultConta.isPresent()) {
                        Double novoSaldo = resultConta.get().getSaldo() + requestDeposito.getValor();
                        resultConta.get().setSaldo(novoSaldo);
                    } else {
                        try {
                            throw new Exception("Conta não encontrada");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
