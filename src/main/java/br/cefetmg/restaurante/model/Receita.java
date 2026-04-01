package br.cefetmg.restaurante.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "receita")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String titulo;

    @Column(nullable = true)
    private Integer porcoes; // serve quantas pessoas

    @Column(nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_cardapio", nullable = false, foreignKey = @ForeignKey(name = "fk_receita_cardapio"))
    private Cardapio cardapio;

    @Builder.Default
    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ReceitaIngrediente> itens = new ArrayList<>();

    // @JsonIgnoreProperties("receitas") // este atributo nao sera incluso no json da classe
    // public List<Ingrediente> getIngredientes() {
    //     if (itens == null) return null;
    //     List<Ingrediente> list = new ArrayList<>();
    //     for (ReceitaIngrediente item : itens)
    //         list.add(item.getIngrediente());
    //     return list;
    // }
}
