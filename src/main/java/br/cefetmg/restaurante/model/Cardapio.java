package br.cefetmg.restaurante.model; 

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cardapio")
public class Cardapio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) 
    private EnumDia descricao;
    
    // @ManyToMany
    // @JoinTable(
    //     name = "cardapio_receita",
    //     joinColumns = @JoinColumn(name = "cardapio_id"),
    //     inverseJoinColumns = @JoinColumn(name = "receita_id")
    // )
    @OneToMany
    @JoinColumn(name = "cardapio_id")
    private List<Receita> receitas;
}
