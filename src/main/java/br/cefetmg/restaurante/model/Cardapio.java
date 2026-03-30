package br.cefetmg.restaurante.model;

import java.util.List;

import lombok.Data;

@Data
public class Cardapio {
    
    private Long id;
    private EnumDia descricao;
    private List<Receita> receitas;
}
