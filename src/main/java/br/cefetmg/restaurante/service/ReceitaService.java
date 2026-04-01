package br.cefetmg.restaurante.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.restaurante.model.Cardapio;
import br.cefetmg.restaurante.model.Ingrediente;
import br.cefetmg.restaurante.model.Receita;
import br.cefetmg.restaurante.model.ReceitaIngrediente;
import br.cefetmg.restaurante.model.ReceitaIngredienteId;
import br.cefetmg.restaurante.repository.CardapioRepository;
import br.cefetmg.restaurante.repository.ReceitaIngredienteRepository;
import br.cefetmg.restaurante.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceitaService {
    
    private final ReceitaRepository receitaRepository;
    private final CardapioRepository cardapioRepository;
    private final ReceitaIngredienteRepository receitaIngredienteRepository;

    public Receita get(Long id) {
        Receita Receita = receitaRepository.findById(id).orElse(null);
        if (Receita == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado Receita com o id: " + id);
        return Receita;
    }

    public List<Receita> getAll() {
        return receitaRepository.findAll();
    }

    public Receita insert(Receita receita) {
        receita.setId(null);
        if (receitaRepository.findByTitulo(receita.getTitulo()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Título já está sendo usada em outra Receita.");

        Cardapio cardapio = cardapioRepository.findById(receita.getCardapio().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado Cardapio com o id: " + receita.getCardapio().getId()));
        receita.setCardapio(cardapio);

        List<ReceitaIngrediente> lista = receita.getItens(); 
        receita.setItens(null);
        Receita auxiliar = receitaRepository.save(receita); // salva receita sem os itens

        if (lista != null)
            for (ReceitaIngrediente item : lista) {
                item = receitaIngredienteRepository.save(new ReceitaIngrediente(new ReceitaIngredienteId(auxiliar.getId(), item.getIngrediente().getId()), auxiliar, item.getIngrediente(), item.getQuantidade()));
            }
        
        return receitaRepository.save(receita);
    }

    // nao funciona -> nao altera a quantidade 
    public Receita update(Receita receita, String quantidade) {
        if (receita.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "O campo id é obrigatório.");
        Receita registro = get(receita.getId()); // verifica se existe registro com este id
        if (!registro.getTitulo().equals(receita.getTitulo()))
            if (receitaRepository.findByTitulo(receita.getTitulo()) != null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Título já está sendo usada em outra Receita.");

        Cardapio cardapio = cardapioRepository.findById(receita.getCardapio().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado Cardapio com o id: " + receita.getCardapio().getId()));
        receita.setCardapio(cardapio);
        
        if (receita.getIngredientes() != null)
            for (Ingrediente item : receita.getIngredientes()) {
                ReceitaIngrediente relacionamento = receitaIngredienteRepository.findById(new ReceitaIngredienteId(receita.getId(), item.getId())).orElse(null);
                if (relacionamento == null)
                    receitaIngredienteRepository.save(new ReceitaIngrediente(new ReceitaIngredienteId(receita.getId(), item.getId()), receita, item, quantidade));
                else {
                    Integer novaQuantidade = Integer.parseInt(relacionamento.getQuantidade()) + Integer.parseInt(quantidade);
                    receitaIngredienteRepository.save(new ReceitaIngrediente(new ReceitaIngredienteId(receita.getId(), item.getId()), receita, item, String.valueOf(novaQuantidade)));
                }
        }

        return receitaRepository.save(receita);
    }

    public Receita delete(Long id) {
        Receita Receita = get(id); // verifica se existe registro com este id
        receitaRepository.deleteById(id);
        return Receita;
    }

    // add ingrediente
    // remove ingrediente
}
