package com.admiro.back_end_carteira.service;

import com.admiro.back_end_carteira.exceptions.CarteiraNaoEncontradaException;
import com.admiro.back_end_carteira.model.Carteira;
import com.admiro.back_end_carteira.model.Usuario;
import com.admiro.back_end_carteira.repository.CarteiraRepository;
import com.admiro.back_end_carteira.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CarteiraService(CarteiraRepository carteiraRepository, UsuarioRepository usuarioRepository) {
        this.carteiraRepository = carteiraRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public UUID criarCarteira(UUID usuarioId, BigDecimal saldoInicial, String moeda) {
        // Verifica se o usuário com o ID fornecido existe
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário com ID " + usuarioId + " não encontrado.");
        }
        
        // Se o usuário existir, cria a carteira
        Carteira carteira = new Carteira();
        carteira.setUsuarioId(usuarioId); // Define o ID do usuário associado à carteira
        carteira.setSaldo(saldoInicial);
        carteira.setMoeda(moeda);
        carteira.setStatus("Ativa"); // Definindo o status inicial como ativo
        LocalDateTime now = LocalDateTime.now();
        carteira.setDataCriacao(now); // Define a data de criação
        carteira.setDataAtualizacao(now); // Define a data de atualização
        
        // Salva a carteira no repositório e retorna o ID
        Carteira carteiraCriada = carteiraRepository.save(carteira);
        return carteiraCriada.getId();
    }
    
    

    public Optional<Carteira> buscarPorId(UUID id) {
        return carteiraRepository.findById(id);
    }

    public List<Carteira> buscarTodasCarteiras() {
        return carteiraRepository.findAll();
    }

    public void atualizarCarteira(UUID carteiraId, Carteira novaCarteira) {
        Optional<Carteira> carteiraOptional = carteiraRepository.findById(carteiraId);
        if (carteiraOptional.isPresent()) {
            Carteira carteira = carteiraOptional.get();
            carteira.setSaldo(novaCarteira.getSaldo());
            carteira.setMoeda(novaCarteira.getMoeda());
            carteira.setStatus(novaCarteira.getStatus());
            carteira.setDataAtualizacao(LocalDateTime.now()); // Atualiza a data de atualização
            carteiraRepository.save(carteira);
        } else {
            throw new CarteiraNaoEncontradaException("Carteira com ID " + carteiraId + " não encontrada.");
        }
    }
    

    // Adicione mais métodos conforme necessário, como atualizar saldo, realizar transações, etc.
}
