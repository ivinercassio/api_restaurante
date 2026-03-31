package br.cefetmg.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private ReceitaIngredienteId id = new ReceitaIngredienteId();

    @ManyToOne
    @MapsId("idReceita")
    @JoinColumn(name = "receita_id")
    @JsonIgnoreProperties("itens")
    private Receita receita;

    @ManyToOne
    @MapsId("idIngrediente")
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @Column(nullable = false)
    private String quantidade; // ex.: 200g, 2 colheres
}
