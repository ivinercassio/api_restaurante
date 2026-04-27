package br.cefetmg.restaurante.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.restaurante.model.security.Permissao;
import br.cefetmg.restaurante.service.PermissaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/permissoes")
@RequiredArgsConstructor
public class PermissaoController {

    private final PermissaoService permissaoService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('PERMISSAO_FULL')")
    public ResponseEntity<List<Permissao>> getAll() {
        List<Permissao> list = permissaoService.getAll();
        return ResponseEntity.ok().body(list);
    }
    
}
