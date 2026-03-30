package br.cefetmg.restaurante.model;

import java.util.List;

public class Receita {
    
    private Long id;
    private String titulo;
    private Integer porcoes; // serve quantas pessoas
    private Double valor;
    private List<Ingrediente> ingredientes;
    
}
