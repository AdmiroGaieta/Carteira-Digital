package com.admiro.back_end_carteira.service;

import com.admiro.back_end_carteira.model.Carteira;
import com.admiro.back_end_carteira.model.Usuario;
import com.admiro.back_end_carteira.repository.CarteiraRepository;
import com.admiro.back_end_carteira.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CarteiraRepository carteiraRepository; // Injeção do CarteiraRepository

    

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, CarteiraRepository carteiraRepository) {
        this.usuarioRepository = usuarioRepository;
        this.carteiraRepository = carteiraRepository; // Inicializa o CarteiraRepository
    }

    public UUID criarUsuario(Usuario usuario) {
        Usuario usuarioCriado = usuarioRepository.save(usuario);
        return usuarioCriado.getId();
    }
    
    

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(UUID id, Usuario novoUsuario) {
        if (usuarioRepository.existsById(id)) {
            novoUsuario.setId(id);
            return usuarioRepository.save(novoUsuario);
        } else {
            return null; // Ou lança uma exceção, dependendo do comportamento desejado
        }
    }

    public boolean deletarUsuario(UUID id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void debitarSaldo(UUID carteiraId, BigDecimal valor) {
        Optional<Carteira> carteiraOptional = carteiraRepository.findById(carteiraId);
        if (carteiraOptional.isPresent()) {
            Carteira carteira = carteiraOptional.get();
            BigDecimal novoSaldo = carteira.getSaldo().subtract(valor);
            carteira.setSaldo(novoSaldo);
            carteiraRepository.save(carteira);
        } else {
            throw new RuntimeException("Carteira não encontrada");
        }
    }

    public void creditarSaldo(UUID carteiraId, BigDecimal valor) {
        Optional<Carteira> carteiraOptional = carteiraRepository.findById(carteiraId);
        if (carteiraOptional.isPresent()) {
            Carteira carteira = carteiraOptional.get();
            BigDecimal novoSaldo = carteira.getSaldo().add(valor);
            carteira.setSaldo(novoSaldo);
            carteiraRepository.save(carteira);
        } else {
            throw new RuntimeException("Carteira não encontrada");
        }
    }
}
