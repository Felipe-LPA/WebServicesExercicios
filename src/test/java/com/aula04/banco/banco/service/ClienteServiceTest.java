package com.aula04.banco.banco.service;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.aula04.banco.banco.BancoAula04Application.bancoCliente;
import static org.mockito.Mockito.when;


public class ClienteServiceTest {
        ClienteService clienteService = new ClienteService();

        @Mock
    BancoCliente bancoCliente;
    @BeforeEach
//    public void init(){
//        MockitoAnnotations.openMocks();
//    }
    @Test
    public void deveRetornarListaCLienteVazia(){
//        when(bancoCliente.buscaClientes()).thenReturn()
        List<Cliente> clientes = clienteService.buscaTodosClientes();
        Assertions.assertTrue(!clientes.isEmpty());
    }
//    @Test
//    public void deveRetornarListaCLienteVazia(){
//        List<Cliente> clientes = clienteService.buscaTodosClientes();
//        Assertions.assertTrue(!clientes.isEmpty());
//    }
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
