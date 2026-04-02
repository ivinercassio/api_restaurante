package br.cefetmg.restaurante.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
    @Builder.Default
    private ReceitaIngredienteId id = new ReceitaIngredienteId();

    @ManyToOne
    @MapsId("idReceita")
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @ManyToOne
    @MapsId("idIngrediente")
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @Column(nullable = false)
    private String quantidade;
}
