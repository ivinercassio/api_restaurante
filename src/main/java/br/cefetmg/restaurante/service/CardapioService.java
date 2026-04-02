package br.cefetmg.restaurante.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.restaurante.model.Cardapio;
import br.cefetmg.restaurante.repository.CardapioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardapioService {

    private final CardapioRepository cardapioRepository;

    public Cardapio get(Long id) {
        return cardapioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado Cardapio com o id: " + id));
    }

    public List<Cardapio> getAll() {
        return cardapioRepository.findAll();
    }

    public Cardapio insert(Cardapio cardapio) {
        cardapio.setId(null);
        return cardapioRepository.save(cardapio);
    }

    public Cardapio update(Cardapio cardapio) {
        if (cardapio.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "O campo id é obrigatório.");
        get(cardapio.getId());
        return cardapioRepository.save(cardapio);
    }

    public Cardapio delete(Long id) {
        Cardapio cardapio = get(id); 
        cardapioRepository.deleteById(id);
        return cardapio;
    }
}
