package br.cefetmg.restaurante.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.restaurante.model.security.Permissao;
import br.cefetmg.restaurante.repository.PermissaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissaoService {
    
    private final PermissaoRepository permissaoRepository;

    public List<Permissao> getAll(){
        return permissaoRepository.findAll();
    }
}
