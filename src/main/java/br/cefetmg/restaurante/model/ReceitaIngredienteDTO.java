package br.cefetmg.restaurante.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaIngredienteDTO {
    private Long idReceita;
    private Long idIngrediente;
    private String quantidade;

    public ReceitaIngredienteDTO(ReceitaIngrediente relacao) {
        idIngrediente = relacao.getIngrediente().getId();
        idReceita = relacao.getReceita().getId();
        quantidade = relacao.getQuantidade();
    }
}
