package com.escoteiro.api.controller;

import com.escoteiro.api.model.ProgressaoClasse;
import com.escoteiro.api.repository.ProgressaoClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progressao-classes")
public class ProgressaoClasseController {

    @Autowired
    private ProgressaoClasseRepository repository;

    @GetMapping
    public List<ProgressaoClasse> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressaoClasse> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgressaoClasse criar(@RequestBody ProgressaoClasse dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgressaoClasse> atualizar(@PathVariable Integer id, @RequestBody ProgressaoClasse dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdProgressao(id);
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
