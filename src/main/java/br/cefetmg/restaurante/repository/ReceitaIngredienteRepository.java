package br.cefetmg.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.cefetmg.restaurante.model.ReceitaIngrediente;
import br.cefetmg.restaurante.model.ReceitaIngredienteId;

@Repository
public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, ReceitaIngredienteId> {

    @Query("SELECT ri from receita_ingrediente ri WHERE ri.receita.id = :id")
    List<ReceitaIngrediente> findAllByReceitaId(@Param("id") Long id);
    
    @Query("SELECT ri from receita_ingrediente ri WHERE ri.ingrediente.id = :id")
    List<ReceitaIngrediente> findAllByIngredienteId(@Param("id") Long id);
}
