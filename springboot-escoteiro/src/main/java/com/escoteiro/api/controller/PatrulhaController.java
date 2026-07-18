package com.escoteiro.api.controller;

import com.escoteiro.api.model.Patrulha;
import com.escoteiro.api.repository.PatrulhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrulhas")
public class PatrulhaController {

    @Autowired
    private PatrulhaRepository repository;

    @GetMapping
    public List<Patrulha> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrulha> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patrulha criar(@RequestBody Patrulha dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrulha> atualizar(@PathVariable Integer id, @RequestBody Patrulha dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdPatrulha(id);
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
