package com.lojagames.sistemagarantia.controllers;

import com.lojagames.sistemagarantia.models.Produto;
import com.lojagames.sistemagarantia.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;  // Para retornar ResponseEntity<T>
import java.util.Optional;                     // Para usar Optional<Produto>
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Cadastrar produto
    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    // Listar produtos por categoria
    @GetMapping("/categoria/{categoria}")
    public List<Produto> listarPorCategoria(@PathVariable String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable Long id,
            @RequestBody Produto produtoAtualizado
    ) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    // Atualiza os campos necessários
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setCategoria(produtoAtualizado.getCategoria());
                    produto.setPreco(produtoAtualizado.getPreco());

                    Produto produtoSalvo = produtoRepository.save(produto);
                    return ResponseEntity.ok(produtoSalvo);
                })
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 se o ID não existir
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (produtoRepository.existsById(id)) { // Verifica se o ID existe
            produtoRepository.deleteById(id);   // Deleta o produto
            return ResponseEntity.noContent().build(); // Retorna HTTP 204 (No Content)
        } else {
            return ResponseEntity.notFound().build(); // Retorna HTTP 404 (Not Found)
        }
    }
}