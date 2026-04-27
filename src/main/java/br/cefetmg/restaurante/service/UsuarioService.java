package br.cefetmg.restaurante.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.restaurante.model.security.Usuario;
import br.cefetmg.restaurante.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario get(Long id) {
         return usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado Usuário com o id: " + id));
    }
    
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario insert(Usuario usuario) {
        usuario.setId(null);
        if (usuarioRepository.findByLogin(usuario.getLogin()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Login já está sendo usado por outro Usuário.");
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        if (usuario.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "O campo id é obrigatório.");
        Usuario registro = get(usuario.getId());
        if (!registro.getLogin().equals(usuario.getLogin()))
            usuarioRepository.findByLogin(usuario.getLogin())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "O Login já está sendo usado por outro Usuário."));
        return usuarioRepository.save(usuario);
    }

    public Usuario delete(Long id) {
        Usuario Usuario = get(id);
        usuarioRepository.deleteById(id);
        return Usuario;
    }
}
