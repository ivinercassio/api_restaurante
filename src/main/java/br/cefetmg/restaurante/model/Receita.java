package br.cefetmg.restaurante.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = true)
    private Integer porcoes; // serve quantas pessoas

    @Column(nullable = false)
    private Double valor;

    @ManyToMany
    @JoinTable(name = "receita_ingrediente", joinColumns = { @JoinColumn(name = "receita_id") }, inverseJoinColumns = {
            @JoinColumn(name = "ingrediente_id") })
    private List<Ingrediente> ingredientes = new ArrayList<>();

}
