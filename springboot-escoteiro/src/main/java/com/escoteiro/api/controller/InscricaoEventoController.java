package com.escoteiro.api.controller;

import com.escoteiro.api.model.InscricaoEvento;
import com.escoteiro.api.repository.InscricaoEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes-eventos")
public class InscricaoEventoController {

    @Autowired
    private InscricaoEventoRepository repository;

    @GetMapping
    public List<InscricaoEvento> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoEvento> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InscricaoEvento criar(@RequestBody InscricaoEvento dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscricaoEvento> atualizar(@PathVariable Integer id, @RequestBody InscricaoEvento dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdInscricao(id);
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
