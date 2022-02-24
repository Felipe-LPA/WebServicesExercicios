package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.ResponseCliente;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    BancoCliente bancoCliente = new BancoCliente();
    Random random = new Random();

    @GetMapping
    public List<ResponseCliente> clientes(){
        return ResponseCliente.toResponse(bancoCliente.buscaCLientes());
    }

    @PostMapping
    public ResponseEntity<ResponseCliente> cadastraCliente(
            @RequestBody RequestCliente requestCliente,
            UriComponentsBuilder uriComponentsBuilder
    ){

        List<Conta> contas = new ArrayList<>();
        Conta conta = new Conta(random.nextInt(), random.nextInt(), TipoConta.CONTA_CORRENTE);
        contas.add(conta);
        Cliente cliente = new Cliente(
                UUID.randomUUID(),
                requestCliente.getNome(),
                requestCliente.getEmail(),
                requestCliente.getSenha(),
                contas);
        bancoCliente.adiciona(cliente);
        URI uri = uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCliente(cliente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCliente> detalhesCliente(@PathVariable UUID id) throws Exception {
        Cliente cliente = bancoCliente.detalhesCliente(id);
        return ResponseEntity.ok( new ResponseCliente(cliente));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCliente> atualizaCLiente(@PathVariable UUID id, @RequestBody RequestCliente requestCliente) throws Exception {
        Cliente cliente = bancoCliente.atualizaCliente(id, requestCliente);
        return ResponseEntity.ok(new ResponseCliente(cliente));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity removeCliente(@PathVariable UUID id) throws Exception {
        bancoCliente.removeCliente(id);
        return ResponseEntity.ok().build();
    }
}
