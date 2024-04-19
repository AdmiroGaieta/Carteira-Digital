package com.admiro.back_end_carteira.controller;

import com.admiro.back_end_carteira.model.Transacao;
import com.admiro.back_end_carteira.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> transferirSaldo(
            @RequestParam UUID remetenteId,
            @RequestParam UUID destinatarioId,
            @RequestParam BigDecimal valor
    ) {
        transacaoService.transferirSaldo(remetenteId, destinatarioId, valor);
        return ResponseEntity.status(HttpStatus.OK).body("Transferência realizada com sucesso.");
    }

    // Outros endpoints para listar, buscar, etc., dependendo das suas necessidades
}
