package com.lojagames.sistemagarantia.repositories;

import com.lojagames.sistemagarantia.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    // Exemplo: Busca vendas por cliente
    List<Venda> findByClienteCpf(Long cpfCliente);
}