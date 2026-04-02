package br.cefetmg.restaurante.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.restaurante.model.Receita;
import br.cefetmg.restaurante.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public Receita get(Long id) {
        return receitaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada Receita com o id: " + id));
    }

    public List<Receita> getAll() {
        return receitaRepository.findAll();
    }

    public Receita insert(Receita receita) {
        receita.setId(null);
        if (receitaRepository.findByTitulo(receita.getTitulo()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Título já está sendo usada em outra Receita.");
        return receitaRepository.save(receita);
    }

    public Receita update(Receita receita) {
        if (receita.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "O campo id é obrigatório.");
        Receita registro = get(receita.getId());
        if (!registro.getTitulo().equals(receita.getTitulo()))
            receitaRepository.findByTitulo(receita.getTitulo())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "O Título já está sendo usada em outra Receita."));
        return receitaRepository.save(receita);
    }

    public Receita delete(Long id) {
        Receita Receita = get(id);
        receitaRepository.deleteById(id);
        return Receita;
    }

}
