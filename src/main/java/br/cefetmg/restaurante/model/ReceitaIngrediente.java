package br.cefetmg.restaurante.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "receita_ingrediente")
public class ReceitaIngrediente {
    
    @EmbeddedId
    private ReceitaIngredienteId id = new ReceitaIngredienteId();

    @ManyToOne
    @MapsId("idReceita")
    private Receita receita;

    @ManyToOne
    @MapsId("idIngrediente")
    private Ingrediente ingrediente;

    @Column(nullable = false)
    private Integer quantidade;
}
