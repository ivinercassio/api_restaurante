package br.cefetmg.restaurante.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.restaurante.model.ReceitaIngredienteDTO;
import br.cefetmg.restaurante.service.ReceitaIngredienteService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/relacoes")
public class ReceitaIngredienteController {
    
    private final ReceitaIngredienteService relacaoService;

    // insert == update, jah que o update de quantidade eh sobrescrevendo o valor
    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('RECEITAINGREDIENTE_POST')")
    public ResponseEntity<ReceitaIngredienteDTO> addIngrediente(@RequestBody ReceitaIngredienteDTO dto) {
        ReceitaIngredienteDTO relacao = relacaoService.addIngrediente(dto);
        return ResponseEntity.ok().body(relacao);
    }
    
    @DeleteMapping("/delete/receita/{idReceita}/ingrediente/{idIngrediente}")
    @PreAuthorize("hasAuthority('RECEITAINGREDIENTE_DELETE')")
    public ResponseEntity<ReceitaIngredienteDTO> addIngrediente(@PathVariable Long idReceita, @PathVariable Long idIngrediente) {
        ReceitaIngredienteDTO relacao = relacaoService.removeIngrediente(idReceita, idIngrediente);
        return ResponseEntity.ok().body(relacao);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('RECEITAINGREDIENTE_GET')")
    public ResponseEntity<List<ReceitaIngredienteDTO>> getAll() {
        List<ReceitaIngredienteDTO> lista = relacaoService.getAll();
        return ResponseEntity.ok().body(lista);
    }
    
    @GetMapping("/receita/{id}")
    @PreAuthorize("hasAuthority('RECEITAINGREDIENTE_GET')")
    public ResponseEntity<List<ReceitaIngredienteDTO>> getAllByReceitaId(@PathVariable Long id) {
        List<ReceitaIngredienteDTO> lista = relacaoService.getAllByReceitaId(id);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/ingrediente/{id}")
    @PreAuthorize("hasAuthority('RECEITAINGREDIENTE_GET')")
    public ResponseEntity<List<ReceitaIngredienteDTO>> getAllByIngredienteId(@PathVariable Long id) {
        List<ReceitaIngredienteDTO> lista = relacaoService.getAllByIngredienteId(id);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/receita/{idReceita}/ingrediente/{idIngrediente}")
    @PreAuthorize("hasAuthority('RECEITAINGREDIENTE_GET')")
    public ResponseEntity<ReceitaIngredienteDTO> getById(@PathVariable Long idReceita, @PathVariable Long idIngrediente) {
        ReceitaIngredienteDTO relacao = relacaoService.getById(idReceita, idIngrediente);
        return ResponseEntity.ok().body(relacao);
    }
}
