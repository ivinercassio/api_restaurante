package br.cefetmg.restaurante.controller;

import br.cefetmg.restaurante.controller.dto.LoginRequest;
import br.cefetmg.restaurante.controller.dto.LoginResponse;
import br.cefetmg.restaurante.model.security.Perfil;
import br.cefetmg.restaurante.model.security.Permissao;
import br.cefetmg.restaurante.model.security.Usuario;
import br.cefetmg.restaurante.service.AuthorizationService;
import br.cefetmg.restaurante.service.PerfilService;
import br.cefetmg.restaurante.service.PermissaoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final PasswordEncoder passwordEncoder;
    
    private final PerfilService perfilService;
    private final PermissaoService permissaoService;

    @PostMapping(value = "/register", consumes = {"application/json"})
    public ResponseEntity<Usuario> register(@RequestBody Usuario data) {
        Usuario usuario = authorizationService.register(data);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest data) {
        LoginResponse loginResponse = authorizationService.login(data, authenticationManager);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping(value = "/encodepwd/{pwd}")
    public ResponseEntity<String> getEncondePwd(@PathVariable String pwd){
        var password = passwordEncoder.encode(pwd);
        return ResponseEntity.ok().body(password);
    }

    @GetMapping("/perfis")
    public ResponseEntity<List<Perfil>> getAllPerfis() {
        List<Perfil> list = perfilService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/permissoes")
    public ResponseEntity<List<Permissao>> getAllPermissoes() {
        List<Permissao> list = permissaoService.getAll();
        return ResponseEntity.ok().body(list);
    }

}
