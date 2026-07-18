package com.escoteiro.api.controller;

import com.escoteiro.api.model.Mensalidade;
import com.escoteiro.api.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensalidades")
public class MensalidadeController {

    @Autowired
    private MensalidadeRepository repository;

    @GetMapping
    public List<Mensalidade> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensalidade> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mensalidade criar(@RequestBody Mensalidade dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensalidade> atualizar(@PathVariable Integer id, @RequestBody Mensalidade dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdMensalidade(id);
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
