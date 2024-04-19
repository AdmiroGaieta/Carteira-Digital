package com.admiro.back_end_carteira.repository;

import com.admiro.back_end_carteira.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarteiraRepository extends JpaRepository<Carteira, UUID> {
    List<Carteira> findAll();
    List<Carteira> findById();
}
