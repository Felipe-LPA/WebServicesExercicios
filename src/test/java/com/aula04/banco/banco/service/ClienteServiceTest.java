package com.aula04.banco.banco.service;

import com.aula04.banco.banco.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClienteServiceTest {
    @Test
    public void deveRetornarListaCLienteVazia(){
        ClienteService clienteService = new ClienteService();
        List<Cliente> clientes = clienteService.buscaTodosClientes();
        Assertions.assertTrue(!clientes.isEmpty());
    }
}
