package br.cefetmg.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.cefetmg.restaurante.model.Cardapio;

@Repository
public interface CardapioRepository extends JpaRepository<Long, Cardapio> {
    
}
