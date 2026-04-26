package br.cefetmg.restaurante.model.security;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome; // Ex: "ADMIN"

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "perfis_permissoes",
            joinColumns = @JoinColumn(name = "perfil_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id")
    )
    private List<Permissao> permissoes;

    @Column(name = "dt_criacao", nullable = false)
    private LocalDateTime dtCriacao;

    @Column(name = "dt_alteracao", nullable = false)
    private LocalDateTime dtAlteracao;

    @PrePersist
    public void prePersist() {
        this.dtCriacao = LocalDateTime.now();
        this.dtAlteracao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dtAlteracao = LocalDateTime.now();
    }
}
