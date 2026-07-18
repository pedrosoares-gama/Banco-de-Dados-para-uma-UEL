package com.escoteiro.api.controller;

import com.escoteiro.api.model.Presenca;
import com.escoteiro.api.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presencas")
public class PresencaController {

    @Autowired
    private PresencaRepository repository;

    @GetMapping
    public List<Presenca> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presenca> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Presenca criar(@RequestBody Presenca dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Presenca> atualizar(@PathVariable Integer id, @RequestBody Presenca dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdPresenca(id);
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
