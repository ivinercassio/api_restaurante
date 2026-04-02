package br.cefetmg.restaurante.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.restaurante.model.Ingrediente;
import br.cefetmg.restaurante.model.Receita;
import br.cefetmg.restaurante.model.ReceitaIngrediente;
import br.cefetmg.restaurante.model.ReceitaIngredienteDTO;
import br.cefetmg.restaurante.model.ReceitaIngredienteId;
import br.cefetmg.restaurante.repository.IngredienteRepository;
import br.cefetmg.restaurante.repository.ReceitaIngredienteRepository;
import br.cefetmg.restaurante.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceitaIngredienteService {

    private final ReceitaIngredienteRepository relacaoRepository;
    private final IngredienteRepository ingredienteRepository;
    private final ReceitaRepository receitaRepository;

    public ReceitaIngredienteDTO addIngrediente(ReceitaIngredienteDTO relacao) {
        Ingrediente ingrediente = ingredienteRepository.findById(relacao.getIdIngrediente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado Ingrediente com id: " + relacao.getIdIngrediente()));
        Receita receita = receitaRepository.findById(relacao.getIdReceita())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrada Receita com id: " + relacao.getIdReceita()));
        if (relacao.getQuantidade() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo Quantidade é obrigatório.");

        ReceitaIngredienteId idRelacao = new ReceitaIngredienteId(relacao.getIdReceita(), relacao.getIdIngrediente());
        relacaoRepository.save(new ReceitaIngrediente(idRelacao, receita, ingrediente, relacao.getQuantidade()));
        return relacao;
    }

    public ReceitaIngredienteDTO removeIngrediente(Long idReceita, Long idIngrediente) {
        ingredienteRepository.findById(idIngrediente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado Ingrediente com id: " + idIngrediente));
        receitaRepository.findById(idReceita)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrada Receita com id: " + idReceita));
        ReceitaIngredienteId idRelacao = new ReceitaIngredienteId(idReceita, idIngrediente);
        ReceitaIngrediente relacao = relacaoRepository.findById(idRelacao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrada associação entre Receita e Ingrediente."));

        relacaoRepository.deleteById(idRelacao);
        return new ReceitaIngredienteDTO(relacao);
    }

    public List<ReceitaIngredienteDTO> getAll() {
        return relacaoRepository.findAll().stream().map(ReceitaIngredienteDTO::new).toList();
    }

    public List<ReceitaIngredienteDTO> getAllByReceitaId(Long id) {
        receitaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrada Receita com id: " + id));
        return relacaoRepository.findAllByReceitaId(id).stream().map(ReceitaIngredienteDTO::new).toList();
    }

    public List<ReceitaIngredienteDTO> getAllByIngredienteId(Long id) {
        receitaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrada Receita com id: " + id));
        return relacaoRepository.findAllByIngredienteId(id).stream().map(ReceitaIngredienteDTO::new).toList();
    }

    public ReceitaIngredienteDTO getById(Long idReceita, Long idIngrediente) {
        ReceitaIngredienteId idRelacao = new ReceitaIngredienteId(idReceita, idIngrediente);
        ReceitaIngrediente relacao = relacaoRepository.findById(idRelacao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrada associação entre Receita e Ingrediente."));
        return new ReceitaIngredienteDTO(relacao);
    }
}
