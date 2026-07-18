package com.escoteiro.api.controller;

import com.escoteiro.api.model.Membro;
import com.escoteiro.api.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membros")
public class MembroController {

    @Autowired
    private MembroRepository repository;

    @GetMapping
    public List<Membro> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membro> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Membro criar(@RequestBody Membro dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membro> atualizar(@PathVariable Integer id, @RequestBody Membro dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdMembro(id);
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
