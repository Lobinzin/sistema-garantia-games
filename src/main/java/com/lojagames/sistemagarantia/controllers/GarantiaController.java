package com.lojagames.sistemagarantia.controllers;

import com.lojagames.sistemagarantia.models.Garantia;
import com.lojagames.sistemagarantia.repositories.GarantiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/garantias")
public class GarantiaController {

    @Autowired
    private GarantiaRepository garantiaRepository;

    // Listar garantias que expiram em até 30 dias
    @GetMapping("/expiracao-proxima")
    public List<Garantia> listarProximasExpiracao() {
        LocalDate dataLimite = LocalDate.now().plusDays(30);
        return garantiaRepository.findByDataExpiracaoBefore(dataLimite);
    }
}