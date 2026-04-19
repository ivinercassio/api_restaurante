package br.cefetmg.restaurante.controller;

import br.cefetmg.restaurante.controller.dto.LoginRequest;
import br.cefetmg.restaurante.controller.dto.LoginResponse;
import br.cefetmg.restaurante.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest data) {
        LoginResponse loginResponse = authorizationService.login(data, authenticationManager);
        return ResponseEntity.ok().body(loginResponse);
    }

/*
    @PostMapping(value = "/register", consumes = {"application/json"})
    public ResponseEntity<Usuario> register(@RequestBody Usuario data) {
        Usuario usuario = authorizationService.register(data);
        return ResponseEntity.ok().body(usuario);
    }
*/

    @PostMapping(value = "/encodepwd/{pwd}")
    public ResponseEntity<String> getEncondePwd(@PathVariable String pwd){
        var password = passwordEncoder.encode(pwd);
        return ResponseEntity.ok().body(password);
    }

}
