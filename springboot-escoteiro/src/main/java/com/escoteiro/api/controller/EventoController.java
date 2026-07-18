package com.escoteiro.api.controller;

import com.escoteiro.api.model.Evento;
import com.escoteiro.api.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository repository;

    @GetMapping
    public List<Evento> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obter(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento criar(@RequestBody Evento dados) {
        return repository.save(dados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable Integer id, @RequestBody Evento dados) {
        return repository.findById(id)
                .map(existente -> {
                    dados.setIdEvento(id);
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
