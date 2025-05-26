package com.lojagames.sistemagarantia.repositories;

import com.lojagames.sistemagarantia.models.Garantia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface GarantiaRepository extends JpaRepository<Garantia, Long> {
    // Exemplo: Busca garantias próximas de expirar
    List<Garantia> findByDataExpiracaoBefore(LocalDate data);
}