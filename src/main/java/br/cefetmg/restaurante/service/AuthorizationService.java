package br.cefetmg.restaurante.service;

import br.cefetmg.restaurante.config.security.TokenService;
import br.cefetmg.restaurante.controller.dto.LoginRequest;
import br.cefetmg.restaurante.controller.dto.LoginResponse;
import br.cefetmg.restaurante.model.security.Token;
import br.cefetmg.restaurante.model.security.Usuario;
import br.cefetmg.restaurante.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private static final String lockedExceptionMessage = "Email não verificado, acessar a caixa de correios e clicar no link de validação.";
    private static final String DisabledExceptionMessage = "Usuario desabilitado, procure o suporte técnico.";
    private static final String AuthenticationExceptionMessage = "Login ou senha não conferem.";

    public LoginResponse login(
            LoginRequest data,
            AuthenticationManager authenticationManager)
    {
        //Criar este objeto utilizado para a autenticação.
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());

        //Buscar o usuario no banco para logar na base de sessions autorizadas e não autorizadas.
        var usuario = usuarioRepository.findByLogin(data.getLogin()).orElse(null);

        //Realiza a autenticação e se der erro captura o erro correto.
        Authentication auth = null;
        try{
            auth = authenticationManager.authenticate(usernamePassword);
        }catch(LockedException e){
            log.error(lockedExceptionMessage + " - " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.LOCKED, lockedExceptionMessage, e);
        }catch(DisabledException e){
            log.error(DisabledExceptionMessage + " - " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.LOCKED, DisabledExceptionMessage, e);
        }catch (AuthenticationException e){
            log.error(AuthenticationExceptionMessage + " - " + e.getMessage() + "(" + e.getClass().getName() + ")");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, AuthenticationExceptionMessage, e);
        }

        Token token = tokenService.generateToken((Usuario) auth.getPrincipal());

        var loginResponseDto = new LoginResponse(token, usuario);

        //var defaultResponseDto = new DefaultResponseDto("Login efetuado com sucesso", 200, loginResponseDto);

        return loginResponseDto;
    }

    /*
    public Usuario register(Usuario data) {
        //Verificar se o usuario já existe
        Usuario usuario = null;
        try{
            usuario = usuarioRepository.findByLogin(data.getLogin()).get();
            if (usuario != null){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Usuário já existente");
            }
        }catch (Throwable e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de banco de dados: " + e.getMessage(), e);
        }

        //codificar a senha e cria usuário para iserir no banco.
        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        usuario = Usuario.builder()
                .nome(data.getNome())
                .login(data.getLogin())
                .password(encryptedPassword)
                .perfis(data.getPerfis())
                .dtCriacao(LocalDateTime.now())
                .dtAlteracao(LocalDateTime.now())
                .build();

        //Tenta inserir no banco de dados o usuario
        try{
            usuarioRepository.save(usuario);
        }catch (Throwable e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de banco de dados: " + e.getMessage(), e);
        }

        return usuario;
    }
     */

    /*
    public DefaultResponseDto sendRecoveryPassWordLink(AuthenticationDto data) {
        return null;
    }

    public DefaultResponseDto sendRecoveryPassWordEnd(AuthenticationDto data) {
        return null;
    }
    */

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDetails user = null;
        try{
            user = usuarioRepository.findByLogin(login).orElse(null);
        }catch (Throwable e){
            throw new UsernameNotFoundException("Erro ao consultar usuário pelo login: " + login + " - " + e.getMessage());
        }

        return user;
    }
}
