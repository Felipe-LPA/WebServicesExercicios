package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.ResponseCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ResponseCliente> clientes(){
        return ResponseCliente.toResponse(clienteService.buscaTodosClientes());
    }

    @PostMapping
    public ResponseEntity<ResponseCliente> cadastraCliente(
            @RequestBody @Valid RequestCliente requestCliente,
            UriComponentsBuilder uriComponentsBuilder
    ){
        Cliente cliente = clienteService.cadastraCliente(requestCliente);
        URI uri = uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCliente(cliente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCliente> detalhesCliente(@PathVariable UUID id) throws Exception {
        Cliente cliente = clienteService.detalhesCliente(id);
        return ResponseEntity.ok( new ResponseCliente(cliente));
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseCliente> atualizaCLiente(@PathVariable UUID id, @RequestBody RequestCliente requestCliente) throws Exception {
//        Cliente cliente = bancoCliente.atualizaCliente(id, requestCliente);
//        return ResponseEntity.ok(new ResponseCliente(cliente));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ResponseCliente> removeCliente(@PathVariable UUID id) throws Exception {
//        bancoCliente.removeCliente(id);
//        return ResponseEntity.ok().build();
//    }
}
