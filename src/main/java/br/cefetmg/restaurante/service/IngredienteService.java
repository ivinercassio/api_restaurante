package br.cefetmg.restaurante.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.restaurante.model.Ingrediente;
import br.cefetmg.restaurante.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredienteService {
    
    private final IngredienteRepository ingredienteRepository;

    public Ingrediente get(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElse(null);
        if (ingrediente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado ingrediente com o id: " + id);
        return ingrediente;
    }

    public List<Ingrediente> getAll() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente insert(Ingrediente ingrediente) {
        ingrediente.setId(null);
        if (ingredienteRepository.findByDescricao(ingrediente.getDescricao()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A descrição já está sendo usada em outro ingrediente.");
        return ingredienteRepository.save(ingrediente);
    }

    public Ingrediente update(Ingrediente ingrediente) {
        if (ingrediente.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "O campo id é obrigatório.");
        Ingrediente registro = get(ingrediente.getId()); 
        if (!registro.getDescricao().equals(ingrediente.getDescricao()))
            if (ingredienteRepository.findByDescricao(ingrediente.getDescricao()) != null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A descrição já está sendo usada em outro ingrediente.");
        return ingredienteRepository.save(ingrediente);
    }

    public Ingrediente delete(Long id) {
        Ingrediente ingrediente = get(id); 
        ingredienteRepository.deleteById(id);
        return ingrediente;
    }
}
