package br.cefetmg.restaurante.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.restaurante.model.security.Perfil;
import br.cefetmg.restaurante.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerfilService {
    
    private final PerfilRepository perfilRepository;

    public List<Perfil> getAll() {
        return perfilRepository.findAll();
    }
}
