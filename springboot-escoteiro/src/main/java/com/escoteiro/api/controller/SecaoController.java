package com.escoteiro.api.controller;

import com.escoteiro.api.model.Secao;
import com.escoteiro.api.repository.SecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secoes")
public class SecaoController {

    @Autowired
    private SecaoRepository repository;

    @GetMapping
    public List<Secao> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Secao> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Secao criar(@RequestBody Secao dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Secao> atualizar(@PathVariable Integer id, @RequestBody Secao dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdSecao(id);
                    return ResponseEntity.ok(repository.save(dados));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
