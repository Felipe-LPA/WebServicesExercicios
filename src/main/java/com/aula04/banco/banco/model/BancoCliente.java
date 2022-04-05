package com.aula04.banco.banco.model;

import com.aula04.banco.banco.dto.RequestCliente;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
        resultCliente.ifPresent(cliente -> clientes.remove(cliente));
    }
    public Optional<Conta> getConta(Cliente cliente, UUID contaId){
        return cliente.getContas().stream().filter(conta -> Objects.equals(conta.getId(),contaId)).findAny();
    }
    public Conta deposita(Cliente cliente, Conta conta) {
        AtomicReference<Conta> resultConta = new AtomicReference<>(new Conta());
        clientes.stream().filter(clienteDB -> Objects.equals(cliente.getId(), clienteDB.getId())).forEach(clienteDB -> {
            clienteDB.getContas().stream().filter(contaDB -> Objects.equals(conta.getId(), contaDB.getId())).forEach(contaDB -> {
                contaDB.setSaldo(conta.getSaldo());
                resultConta.set(contaDB);
            });
        });
        return resultConta.get();
    }
}
