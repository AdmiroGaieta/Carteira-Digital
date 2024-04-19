package com.admiro.back_end_carteira.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.admiro.back_end_carteira.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    @JsonProperty("nome") // Mapeia o campo JSON "nome" para o atributo "nome" na classe Java
    private String nome;

    @Column(nullable = false, unique = true) // Adicionando a restrição unique ao campo email
    @JsonProperty("email")
    private String email;

    @Column(nullable = false, unique = true) // Adicionando a restrição unique ao campo email
    @JsonProperty("bi")
    private String bi;

    @Column(nullable = false)
    @JsonProperty("senha") // Mapeia o campo JSON "senha" para o atributo "senha" na classe Java
    private String senha;

    @Column(nullable = false)
    @JsonProperty("tipo") // Mapeia o campo JSON "tipo" para o atributo "tipo" na classe Java
    private TipoUsuario tipo; // Pode ser "gerente" ou "comum"

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public UUID getId() {
        return this.id;
    }

    public void setbi(String bi) {
        this.bi = bi;
    }

    public String getbi() {
        return this.bi;
    }

    public void setSaldo(BigDecimal saldo) {
         this.saldo=saldo;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    // Getters e Setters
    // Métodos omitidos para brevidade
}
