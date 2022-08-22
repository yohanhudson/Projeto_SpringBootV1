package com.example.StefFood.controller;

import com.example.StefFood.dto.ClienteDto;
import com.example.StefFood.dto.LojaDto;
import com.example.StefFood.form.AtualizarCliente;
import com.example.StefFood.form.ClienteForm;
import com.example.StefFood.modelo.Cliente;
import com.example.StefFood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    public ClienteRepository clienteRepository;

    @GetMapping
    public List<ClienteDto> lista() {
        List<Cliente> clientes = clienteRepository.findAll();

        return ClienteDto.converter(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        Cliente cliente = form.converter(clienteRepository);
        clienteRepository.save(cliente);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

    @GetMapping("/{id}")
    @Transactional
    public ClienteDto detalhar(@PathVariable Long id) {
        Cliente cliente = clienteRepository.getOne(id);

        return new ClienteDto(cliente);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarCliente form) {
        Cliente cliente = form.atualizar(id, clienteRepository);

        return ResponseEntity.ok(new ClienteDto(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        clienteRepository.deleteById(id);

        return ResponseEntity.ok("Cliente deletada com sucesso");
    }
}
