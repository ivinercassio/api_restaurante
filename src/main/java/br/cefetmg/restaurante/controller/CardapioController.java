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

import br.cefetmg.restaurante.model.Cardapio;
import br.cefetmg.restaurante.service.CardapioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/cardapios")
public class CardapioController {

    private final CardapioService cardapioService;
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CARDAPIO_GET')")
    public ResponseEntity<Cardapio> get(@PathVariable Long id) {
        Cardapio cardapio = cardapioService.get(id);
        return ResponseEntity.ok().body(cardapio);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('CARDAPIO_GET')")
    public ResponseEntity<List<Cardapio>> getAll() {
        List<Cardapio> lista = cardapioService.getAll();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('CARDAPIO_POST')")
    public ResponseEntity<Cardapio> insert(@RequestBody Cardapio cardapio) {
        Cardapio registro = cardapioService.insert(cardapio);
        return ResponseEntity.ok().body(registro);
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('CARDAPIO_PUT')")
    public ResponseEntity<Cardapio> update(@RequestBody Cardapio cardapio) {
        Cardapio registro = cardapioService.update(cardapio);
        return ResponseEntity.ok().body(registro);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CARDAPIO_DELETE')")
    public ResponseEntity<Cardapio> delete(@PathVariable Long id) {
        Cardapio cardapio = cardapioService.delete(id);
        return ResponseEntity.ok().body(cardapio);
    }
}
