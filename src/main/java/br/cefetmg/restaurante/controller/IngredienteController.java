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

import br.cefetmg.restaurante.model.Ingrediente;
import br.cefetmg.restaurante.service.IngredienteService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ingredientes")
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('INGREDIENTE_GET')")
    public ResponseEntity<Ingrediente> get(@PathVariable Long id) {
        Ingrediente ingrediente = ingredienteService.get(id);
        return ResponseEntity.ok().body(ingrediente);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('INGREDIENTE_GET')")
    public ResponseEntity<List<Ingrediente>> getAll() {
        List<Ingrediente> lista = ingredienteService.getAll();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('INGREDIENTE_POST')")
    public ResponseEntity<Ingrediente> insert(@RequestBody Ingrediente ingrediente) {
        Ingrediente registro = ingredienteService.insert(ingrediente);
        return ResponseEntity.ok().body(registro);
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('INGREDIENTE_PUT')")
    public ResponseEntity<Ingrediente> update(@RequestBody Ingrediente ingrediente) {
        Ingrediente registro = ingredienteService.update(ingrediente);
        return ResponseEntity.ok().body(registro);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('INGREDIENTE_DELETE')")
    public ResponseEntity<Ingrediente> delete(@PathVariable Long id) {
        Ingrediente ingrediente = ingredienteService.delete(id);
        return ResponseEntity.ok().body(ingrediente);
    }
    
}
