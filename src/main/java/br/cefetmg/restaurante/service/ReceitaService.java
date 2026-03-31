package br.cefetmg.restaurante.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.restaurante.model.Receita;
import br.cefetmg.restaurante.model.ReceitaIngrediente;
import br.cefetmg.restaurante.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceitaService {
    
    private final ReceitaRepository receitaRepository;

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

        // OPERACOES MANUAIS
        List<ReceitaIngrediente> itens = receita.getItens();
        receita.setItens(null);
        // receita.getCardapio().getId(); // consultar se cardapio existe
        for (ReceitaIngrediente item : itens) {
            receita.addIngrediente(item.getIngrediente(), item.getQuantidade());
        }
        return receitaRepository.save(receita);
    }

    public Receita update(Receita receita) {
        if (receita.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "O campo id é obrigatório.");
        Receita registro = get(receita.getId()); // verifica se existe registro com este id
        if (!registro.getTitulo().equals(receita.getTitulo()))
            if (receitaRepository.findByTitulo(receita.getTitulo()) != null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Título já está sendo usada em outra Receita.");
        return receitaRepository.save(receita);
    }

    public Receita delete(Long id) {
        Receita Receita = get(id); // verifica se existe registro com este id
        receitaRepository.deleteById(id);
        return Receita;
    }
}
