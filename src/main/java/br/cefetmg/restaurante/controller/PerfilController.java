package br.cefetmg.restaurante.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.restaurante.model.security.Perfil;
import br.cefetmg.restaurante.service.PerfilService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('FULL_CONTROL')")
    public ResponseEntity<List<Perfil>> getAll() {
        List<Perfil> list = perfilService.getAll();
        return ResponseEntity.ok().body(list);
    }
}
