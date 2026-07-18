package com.escoteiro.api.controller;

import com.escoteiro.api.model.Especialidade;
import com.escoteiro.api.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeRepository repository;

    @GetMapping
    public List<Especialidade> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidade criar(@RequestBody Especialidade dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Integer id, @RequestBody Especialidade dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdEspecialidade(id);
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
