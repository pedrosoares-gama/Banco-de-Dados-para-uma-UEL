package com.escoteiro.api.controller;

import com.escoteiro.api.model.Atividade;
import com.escoteiro.api.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeRepository repository;

    @GetMapping
    public List<Atividade> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Atividade criar(@RequestBody Atividade dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> atualizar(@PathVariable Integer id, @RequestBody Atividade dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdAtividade(id);
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
