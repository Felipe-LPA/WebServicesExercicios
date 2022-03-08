package com.aula04.banco.banco.service;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClienteServiceTest {
        ClienteService clienteService = new ClienteService();
    @Test
    public void deveRetornarListaCLienteVazia(){
        List<Cliente> clientes = clienteService.buscaTodosClientes();
        Assertions.assertTrue(!clientes.isEmpty());
    }
    @Test
    public void cadastraCliente(){
        RequestCliente requestCliente = new RequestCliente(
        "",
                "Felipe@gmail.com",
                "sad"
        );
        Cliente cliente = clienteService.cadastraCliente(requestCliente);
        Assertions.assertEquals(requestCliente.getNome(), cliente.getNome());
        Assertions.assertNotNull(cliente.getId());
    }
}
