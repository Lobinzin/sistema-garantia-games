package com.lojagames.sistemagarantia.repositories;

import com.lojagames.sistemagarantia.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria(String categoria); // Busca produtos por categoria
}