package com.admiro.back_end_carteira.service;

import com.admiro.back_end_carteira.enums.TipoTransacao;
import com.admiro.back_end_carteira.model.Carteira;
import com.admiro.back_end_carteira.model.Transacao;
import com.admiro.back_end_carteira.model.Usuario;
import com.admiro.back_end_carteira.repository.CarteiraRepository;
import com.admiro.back_end_carteira.repository.TransacaoRepository;
import com.admiro.back_end_carteira.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransacaoService {

    private final CarteiraRepository carteiraRepository;
    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TransacaoService(CarteiraRepository carteiraRepository, TransacaoRepository transacaoRepository, UsuarioRepository usuarioRepository) {
        this.carteiraRepository = carteiraRepository;
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void transferirSaldo(UUID remetenteId, UUID destinatarioId, BigDecimal valor) {
        Optional<Carteira> remetenteCarteiraOptional = carteiraRepository.findById(remetenteId);
        Optional<Carteira> destinatarioCarteiraOptional = carteiraRepository.findById(destinatarioId);

        if (remetenteCarteiraOptional.isPresent() && destinatarioCarteiraOptional.isPresent()) {
            Carteira remetenteCarteira = remetenteCarteiraOptional.get();
            Carteira destinatarioCarteira = destinatarioCarteiraOptional.get();

            BigDecimal saldoRemetente = remetenteCarteira.getSaldo();
            BigDecimal saldoDestinatario = destinatarioCarteira.getSaldo();

            if (saldoRemetente.compareTo(valor) >= 0) {
                // Realiza a transferência
                remetenteCarteira.setSaldo(saldoRemetente.subtract(valor));
                destinatarioCarteira.setSaldo(saldoDestinatario.add(valor));

                carteiraRepository.save(remetenteCarteira);
                carteiraRepository.save(destinatarioCarteira);

                // Registra a transação
                Transacao transacao = new Transacao(
                        remetenteCarteira.getUsuario(),
                        destinatarioCarteira.getUsuario(),
                        valor,
                        TipoTransacao.CREDITO
                );
                transacaoRepository.save(transacao);
            } else {
                throw new RuntimeException("Saldo insuficiente para realizar a transferência.");
            }
        } else {
            throw new RuntimeException("Carteira do remetente ou destinatário não encontrada.");
        }
    }
}
