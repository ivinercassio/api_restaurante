package br.cefetmg.restaurante.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPerfil {
    private Usuario usuario;
    private Perfil perfil;
}
