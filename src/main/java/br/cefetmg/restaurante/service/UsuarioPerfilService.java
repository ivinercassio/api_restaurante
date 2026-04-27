package br.cefetmg.restaurante.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.restaurante.model.security.Perfil;
import br.cefetmg.restaurante.model.security.Usuario;
import br.cefetmg.restaurante.model.security.UsuarioPerfil;
import br.cefetmg.restaurante.model.security.UsuarioPerfilDTO;
import br.cefetmg.restaurante.repository.PerfilRepository;
import br.cefetmg.restaurante.repository.UsuarioPerfilRepository;
import br.cefetmg.restaurante.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioPerfilService {

    private final UsuarioPerfilRepository relacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    public UsuarioPerfilDTO addPerfil(UsuarioPerfilDTO usuarioPerfil) {
        Usuario usuario = usuarioRepository.findById(usuarioPerfil.getUsuario_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado Usuário com id: " + usuarioPerfil.getUsuario_id()));
        Perfil perfil = perfilRepository.findById(usuarioPerfil.getPerfil_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado Perfil com id: " + usuarioPerfil.getPerfil_id()));
        relacaoRepository.save(new UsuarioPerfil(usuario, perfil));
        return usuarioPerfil;
    }

    public UsuarioPerfilDTO removePerfil(Long idUsuario, Long idPerfil) {
        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado Usuário com id: " + idUsuario));
        perfilRepository.findById(idPerfil)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado Perfil com id: " + idPerfil));
        UsuarioPerfil registro = relacaoRepository.findByIds(idUsuario, idPerfil)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrada associação entre Usuário e Perfil"));
        relacaoRepository.delete(registro);
        return new UsuarioPerfilDTO(registro);
    }

    public List<UsuarioPerfilDTO> getAll() {
        return relacaoRepository.findAll().stream().map(UsuarioPerfilDTO::new).toList();
    }

    public List<UsuarioPerfilDTO> getAllByUsuarioId(Long idUsuario) {
        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado Usuário com id: " + idUsuario));
        return relacaoRepository.findAllByUsuarioId(idUsuario).stream().map(UsuarioPerfilDTO::new).toList();
    }

    public List<UsuarioPerfilDTO> getAllByPerfilId(Long idPerfil) {
        perfilRepository.findById(idPerfil)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado Perfil com id: " + idPerfil));
        return relacaoRepository.findAllByPerfilId(idPerfil).stream().map(UsuarioPerfilDTO::new).toList();
    }

    public UsuarioPerfilDTO getByIds(Long idUsuario, Long idPerfil) {
        UsuarioPerfil registro = relacaoRepository.findByIds(idUsuario, idPerfil)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrada associação entre Usuário e Perfil"));
        return new UsuarioPerfilDTO(registro);
    }
}
