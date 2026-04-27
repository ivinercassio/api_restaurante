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

import br.cefetmg.restaurante.model.security.Usuario;
import br.cefetmg.restaurante.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v2/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_GET')")
    public ResponseEntity<Usuario> get(@PathVariable Long id) {
        Usuario usuario = usuarioService.get(id);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('USUARIO_GET')")
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> lista = usuarioService.getAll();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('USUARIO_POST')")
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
        Usuario registro = usuarioService.insert(usuario);
        return ResponseEntity.ok().body(registro);
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('USUARIO_PUT')")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
        Usuario registro = usuarioService.update(usuario);
        return ResponseEntity.ok().body(registro);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_DELETE')")
    public ResponseEntity<Usuario> delete(@PathVariable Long id) {
        Usuario usuario = usuarioService.delete(id);
        return ResponseEntity.ok().body(usuario);
    }
}
