package br.cefetmg.restaurante.controller.dto;

import br.cefetmg.restaurante.model.security.Token;
import br.cefetmg.restaurante.model.security.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private Token token;
    private Usuario usuario;
}
