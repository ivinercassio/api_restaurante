package br.cefetmg.restaurante.model.security;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsuarioPerfilId implements Serializable {
    
    @Column(name = "usuario_id")
    private Long usuarioId;
    
    @Column(name = "perfil_id")
    private Long perfilId;
}