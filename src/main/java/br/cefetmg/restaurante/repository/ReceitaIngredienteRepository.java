package br.cefetmg.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.cefetmg.restaurante.model.Ingrediente;
import br.cefetmg.restaurante.model.Receita;
import br.cefetmg.restaurante.model.ReceitaIngrediente;
import br.cefetmg.restaurante.model.ReceitaIngredienteId;

@Repository
public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, ReceitaIngredienteId> {

    List<Ingrediente> findByReceitaId(Long id);

    List<Receita> findByIngredienteId(Long id);
}
