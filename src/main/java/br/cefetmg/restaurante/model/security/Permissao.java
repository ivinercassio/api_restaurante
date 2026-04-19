package br.cefetmg.restaurante.model.security;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

@Data
@Entity(name = "permissao")
public class Permissao implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome; // Ex: "PET_ESCRITA"

    @Override
    public String getAuthority() {
        return this.nome;
    }

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
