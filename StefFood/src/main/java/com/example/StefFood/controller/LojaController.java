package com.example.StefFood.controller;


import com.example.StefFood.dto.LojaDto;
import com.example.StefFood.form.AtualizarLoja;
import com.example.StefFood.form.LojaForm;
import com.example.StefFood.modelo.Loja;
import com.example.StefFood.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaRepository lojaRepository;


    @GetMapping
    public List<LojaDto> lista(){
        List<Loja> lojas = lojaRepository.findAll();
        return LojaDto.converter(lojas);
    }

    @PostMapping
    public ResponseEntity<LojaDto> cadastrar(@RequestBody @Valid LojaForm form, UriComponentsBuilder uriBuilder){
        Loja loja = form.converter(lojaRepository);
        lojaRepository.save(loja);

        URI uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();
        return ResponseEntity.created(uri).body(new LojaDto(loja));
    }

    @GetMapping("/{id}")
    @Transactional
    public LojaDto detalhar(@PathVariable Long id) {
        Loja loja = lojaRepository.getOne(id);

        return new LojaDto(loja);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<LojaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarLoja form) {
        Loja loja = form.atualizar(id, lojaRepository);

        return ResponseEntity.ok(new LojaDto(loja));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        lojaRepository.deleteById(id);

        return ResponseEntity.ok("Loja deletada com sucesso");
    }
}
