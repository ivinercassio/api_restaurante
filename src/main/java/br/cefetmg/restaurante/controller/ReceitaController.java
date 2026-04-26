package br.cefetmg.restaurante.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.restaurante.model.Receita;
import br.cefetmg.restaurante.service.ReceitaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/receitas")
public class ReceitaController {
    
    private final ReceitaService receitaService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RECEITA_GET')")
    public ResponseEntity<Receita> get(@PathVariable Long id) {
        Receita receita = receitaService.get(id);
        return ResponseEntity.ok().body(receita);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('RECEITA_GET')")
    public ResponseEntity<List<Receita>> getAll() {
        List<Receita> lista = receitaService.getAll();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('RECEITA_POST')")
    public ResponseEntity<Receita> insert(@RequestBody Receita receita) {
        Receita registro = receitaService.insert(receita);
        return ResponseEntity.ok().body(registro);
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('RECEITA_PUT')")
    public ResponseEntity<Receita> update(@RequestBody Receita receita) {
        Receita registro = receitaService.update(receita);
        return ResponseEntity.ok().body(registro);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('RECEITA_DELETE')")
    public ResponseEntity<Receita> delete(@PathVariable Long id) {
        Receita receita = receitaService.delete(id);
        return ResponseEntity.ok().body(receita);
    }

}
