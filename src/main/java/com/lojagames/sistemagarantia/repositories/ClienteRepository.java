package com.lojagames.sistemagarantia.repositories;

import com.lojagames.sistemagarantia.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos adicionais (se necessário):
    Cliente findByCpf(Long cpf); // Busca cliente por CPF
}