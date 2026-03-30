package br.cefetmg.restaurante.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReceitaIngredienteId implements Serializable{
    private Long idReceita;
    private Long idIngrediente;
}
