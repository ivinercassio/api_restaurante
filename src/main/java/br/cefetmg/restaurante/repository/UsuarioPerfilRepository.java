package br.cefetmg.restaurante.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.cefetmg.restaurante.model.security.UsuarioPerfil;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {
    @Query("SELECT up from usuarios_perfis up WHERE up.perfil.id = :id")
    List<UsuarioPerfil> findAllByPerfilId(@Param("id") Long id);
    
    @Query("SELECT up from usuarios_perfis up WHERE up.usuario.id = :id")
    List<UsuarioPerfil> findAllByUsuarioId(@Param("id") Long id);    

    @Query("SELECT up from usuarios_perfis up WHERE up.usuario.id = :idU AND up.perfil.id = :idP")
    Optional<UsuarioPerfil> findByIds(@Param("idU") Long idU, @Param("idP") Long idP);    
}
