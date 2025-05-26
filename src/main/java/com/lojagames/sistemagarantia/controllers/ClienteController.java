package com.lojagames.sistemagarantia.controllers;

import com.lojagames.sistemagarantia.models.Cliente;
import com.lojagames.sistemagarantia.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Metodo deletar clientes
    @DeleteMapping("/{cpf}") // Mapeia DELETE /api/clientes/{cpf}
    public ResponseEntity<Void> deletarCliente(@PathVariable Long cpf) {
        if (clienteRepository.existsById(cpf)) {
            clienteRepository.deleteById(cpf);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se o CPF não existir
    }

    // Metodo PUT para atualizar cliente
    @PutMapping("/{cpf}")
    public ResponseEntity<Cliente> atualizarCliente(
            @PathVariable Long cpf,
            @RequestBody Cliente clienteAtualizado) {

        return clienteRepository.findById(cpf)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    Cliente clienteSalvo = clienteRepository.save(cliente);
                    return ResponseEntity.ok(clienteSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Cadastrar cliente
    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    // Listar todos os clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable Long cpf) {
        return clienteRepository.findById(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}