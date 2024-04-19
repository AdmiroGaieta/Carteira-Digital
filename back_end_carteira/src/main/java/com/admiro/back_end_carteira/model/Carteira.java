package com.admiro.back_end_carteira.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "carteira")
public class Carteira {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID usuarioId;

    @Column(nullable = false)
    private BigDecimal saldo = BigDecimal.ZERO;

    @Column(nullable = false)
    private String moeda;

    @Column(nullable = false)
    private String status;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "limite_credito")
    private BigDecimal limiteCredito;


    // Getters e Setters
    // Métodos omitidos para brevidade
    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // Getters e Setters
    // Métodos omitidos para brevidade

    public UUID getUsuarioId() {
        return this.usuarioId;
    }
    
    public BigDecimal getSaldo() {
        return this.saldo;
    }
    
    public String getMoeda() {
        return this.moeda;
    }

    public UUID getId() {
       return this.id; 
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao =dataAtualizacao;
    }

    public void setId(UUID id) {
         this.id=id;
    }

    public String getStatus() {
       return this.status;
    }
    
}
