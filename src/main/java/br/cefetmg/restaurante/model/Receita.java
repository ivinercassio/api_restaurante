package br.cefetmg.restaurante.model;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "receita")
    private List<ReceitaIngrediente> itens = new ArrayList<>();

    public void addIngrediente(Ingrediente ingrediente, String quantidade) {
        ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();
        receitaIngrediente.setId(new ReceitaIngredienteId(this.getId(), ingrediente.getId()));
        receitaIngrediente.setIngrediente(ingrediente);
        receitaIngrediente.setQuantidade(quantidade);
        receitaIngrediente.setReceita(this);
        this.itens.add(receitaIngrediente);
    }

    // public Receita removerIngrediente(@PathVariable Long id) {}
}
