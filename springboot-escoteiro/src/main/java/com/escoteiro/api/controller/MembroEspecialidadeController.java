package com.escoteiro.api.controller;

import com.escoteiro.api.model.MembroEspecialidade;
import com.escoteiro.api.repository.MembroEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membro-especialidades")
public class MembroEspecialidadeController {

    @Autowired
    private MembroEspecialidadeRepository repository;

    @GetMapping
    public List<MembroEspecialidade> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembroEspecialidade> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MembroEspecialidade criar(@RequestBody MembroEspecialidade dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembroEspecialidade> atualizar(@PathVariable Integer id, @RequestBody MembroEspecialidade dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdMembroEspecialidade(id);
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
