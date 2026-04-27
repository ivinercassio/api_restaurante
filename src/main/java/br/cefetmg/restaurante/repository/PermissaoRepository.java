package br.cefetmg.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.cefetmg.restaurante.model.security.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}
