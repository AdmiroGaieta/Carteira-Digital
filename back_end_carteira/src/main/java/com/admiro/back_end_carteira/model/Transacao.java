package com.admiro.back_end_carteira.model;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import jakarta.persistence.Id;

import com.admiro.back_end_carteira.enums.StatusTransacao;
import com.admiro.back_end_carteira.enums.TipoTransacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
 
 

@Entity
public class Transacao {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne
    private Usuario remetente;
    
    @ManyToOne
    private Usuario destinatario;
    
    private BigDecimal valor;
    
    private LocalDateTime dataHora;
    
    private StatusTransacao status;
    
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo; // Para distinguir entre crédito e débito


    // Construtor
    public Transacao(Usuario remetente, Usuario destinatario, BigDecimal valor, TipoTransacao tipo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valor = valor;
        this.tipo = tipo;
        this.dataHora = LocalDateTime.now(); // Define a data e hora atual
        this.status = StatusTransacao.PENDENTE; // Define o status como pendente por padrão
    }


    // Getters e Setters
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public void setStatus(StatusTransacao status) {
        this.status = status;
    }
    
    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }
}
