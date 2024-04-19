package com.admiro.back_end_carteira.model;

import java.math.BigDecimal;

import com.admiro.back_end_carteira.enums.TipoTransacao;

public class Debito extends Transacao {

    public Debito(Usuario remetente, Usuario destinatario, BigDecimal valor, TipoTransacao tipo) {
        super(remetente, destinatario, valor, tipo);
    }
}

