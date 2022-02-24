package com.aula04.banco.banco.model;

import com.aula04.banco.banco.dto.RequestCliente;

import java.util.*;

public class BancoCliente {
    private static List<Cliente> clientes = new ArrayList<>();
    public void adiciona(Cliente cliente){
        BancoCliente.clientes.add(cliente);
    }
    public List<Cliente> buscaCLientes(){
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
    public Cliente removeCliente(UUID id) throws Exception{
        Optional<Cliente> resultCliente = clientes.stream().filter(cliente -> Objects.equals(cliente.getId(), id)).findAny();
        if(resultCliente.isPresent()){
            clientes.remove(resultCliente.get());
            return resultCliente.get();
        }
        throw new Exception("Usuário não encontrádo");
    }
}
