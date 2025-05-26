package com.lojagames.sistemagarantia.controllers;

import com.lojagames.sistemagarantia.models.Cliente;
import com.lojagames.sistemagarantia.models.Produto;
import com.lojagames.sistemagarantia.models.Venda;
import com.lojagames.sistemagarantia.repositories.VendaRepository;
import com.lojagames.sistemagarantia.repositories.ClienteRepository;
import com.lojagames.sistemagarantia.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<Venda> registrarVenda(@RequestBody Venda venda) {
        // Verifica se cliente e produto existem
        if (venda.getCliente() == null || venda.getProduto() == null ||
                venda.getCliente().getCpf() == null || venda.getProduto().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Carrega entidades completas do banco
        Cliente cliente = clienteRepository.findById(venda.getCliente().getCpf()).orElse(null);
        Produto produto = produtoRepository.findById(venda.getProduto().getId()).orElse(null);

        if (cliente == null || produto == null) {
            return ResponseEntity.notFound().build();
        }

        venda.setCliente(cliente);
        venda.setProduto(produto);

        Venda vendaSalva = vendaRepository.save(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
    }
}