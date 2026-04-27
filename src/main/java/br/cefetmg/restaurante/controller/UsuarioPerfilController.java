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

import br.cefetmg.restaurante.model.security.UsuarioPerfilDTO;
import br.cefetmg.restaurante.service.UsuarioPerfilService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/usuario-perfil")
@RequiredArgsConstructor
public class UsuarioPerfilController {
    
    private final UsuarioPerfilService relacaoService;

    // insert == update, jah que o update de quantidade eh sobrescrevendo o valor
    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('USUARIOPERFIL_POST')")
    public ResponseEntity<UsuarioPerfilDTO> addPerfil(@RequestBody UsuarioPerfilDTO dto) {
        UsuarioPerfilDTO relacao = relacaoService.addPerfil(dto);
        return ResponseEntity.ok().body(relacao);
    }
    
    @DeleteMapping("/delete/usuario/{idUsuario}/perfil/{idPerfil}")
    @PreAuthorize("hasAuthority('USUARIOPERFIL_DELETE')")
    public ResponseEntity<UsuarioPerfilDTO> removePerfil(@PathVariable Long idUsuario, @PathVariable Long idPerfil) {
        UsuarioPerfilDTO relacao = relacaoService.removePerfil(idUsuario, idPerfil);
        return ResponseEntity.ok().body(relacao);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('USUARIOPERFIL_GET')")
    public ResponseEntity<List<UsuarioPerfilDTO>> getAll() {
        List<UsuarioPerfilDTO> lista = relacaoService.getAll();
        return ResponseEntity.ok().body(lista);
    }
    
    @GetMapping("/usuario/{id}")
    @PreAuthorize("hasAuthority('USUARIOPERFIL_GET')")
    public ResponseEntity<List<UsuarioPerfilDTO>> getAllByUsuarioId(@PathVariable Long id) {
        List<UsuarioPerfilDTO> lista = relacaoService.getAllByUsuarioId(id);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/perfil/{id}")
    @PreAuthorize("hasAuthority('USUARIOPERFIL_GET')")
    public ResponseEntity<List<UsuarioPerfilDTO>> getAllByPerfilId(@PathVariable Long id) {
        List<UsuarioPerfilDTO> lista = relacaoService.getAllByPerfilId(id);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/usuario/{idUsuario}/perfil/{idPerfil}")
    @PreAuthorize("hasAuthority('USUARIOPERFIL_GET')")
    public ResponseEntity<UsuarioPerfilDTO> getById(@PathVariable Long idUsuario, @PathVariable Long idPerfil) {
        UsuarioPerfilDTO relacao = relacaoService.getByIds(idUsuario, idPerfil);
        return ResponseEntity.ok().body(relacao);
    }
}
