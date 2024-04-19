package com.admiro.back_end_carteira.repository;

import com.admiro.back_end_carteira.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
