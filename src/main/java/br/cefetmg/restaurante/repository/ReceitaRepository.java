package br.cefetmg.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.cefetmg.restaurante.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    
} 
