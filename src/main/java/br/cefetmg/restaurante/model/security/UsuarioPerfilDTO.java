package br.cefetmg.restaurante.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioPerfilDTO {
    private Long perfil_id;
    private Long usuario_id;

    public UsuarioPerfilDTO(UsuarioPerfil relacao) {
        this.usuario_id = relacao.getId().getUsuarioId();
        this.perfil_id = relacao.getId().getPerfilId();
    }    
}
