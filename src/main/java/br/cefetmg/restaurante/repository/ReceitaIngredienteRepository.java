package br.cefetmg.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.cefetmg.restaurante.model.ReceitaIngrediente;
import br.cefetmg.restaurante.model.ReceitaIngredienteId;

@Repository
public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, ReceitaIngredienteId> {
}
