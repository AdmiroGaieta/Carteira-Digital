package com.admiro.back_end_carteira.repository;

import com.admiro.back_end_carteira.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
}
