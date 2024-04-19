package com.admiro.back_end_carteira.controller;

import com.admiro.back_end_carteira.model.Carteira;
import com.admiro.back_end_carteira.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/carteiras")
public class CarteiraController {

    private final CarteiraService carteiraService;

    @Autowired
    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping
    public ResponseEntity<UUID> criarCarteira(@RequestBody Carteira carteira) {
        if (carteira.getUsuarioId() == null || carteira.getSaldo() == null || carteira.getMoeda() == null) {
            return ResponseEntity.badRequest().build();
        }

        UUID carteiraId = carteiraService.criarCarteira(carteira.getUsuarioId(), carteira.getSaldo(), carteira.getMoeda());
        return ResponseEntity.status(HttpStatus.CREATED).body(carteiraId);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Carteira> buscarCarteiraPorId(@PathVariable UUID id) {
        Optional<Carteira> carteiraOptional = carteiraService.buscarPorId(id);
        return carteiraOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<Carteira>> buscarTodasCarteiras() {
        List<Carteira> carteiras = carteiraService.buscarTodasCarteiras();
        return ResponseEntity.ok(carteiras);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCarteira(@PathVariable UUID id, @RequestBody Carteira carteira) {
        carteiraService.atualizarCarteira(id, carteira);
        return ResponseEntity.noContent().build();
    }

    // Adicione mais métodos conforme necessário, como atualização de saldo, transações, etc.
}
