// URL base da API (ajuste conforme necessário)
const API_URL = 'http://localhost:8080/api';

// Função para carregar clientes
async function carregarClientes() {
    try {
        const response = await fetch(`${API_URL}/clientes`);
        const clientes = await response.json();

        const tabela = document.getElementById('tabelaClientes');
        tabela.innerHTML = clientes.map(cliente => `
            <tr>
                <td>${cliente.cpf}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.telefone || '-'}</td>
            </tr>
        `).join('');
    } catch (error) {
        console.error("Erro ao carregar clientes:", error);
    }
}

// Cadastrar novo cliente
document.getElementById('formCliente').addEventListener('submit', async (e) => {
    e.preventDefault();

    const cliente = {
        cpf: document.getElementById('cpf').value,
        nome: document.getElementById('nome').value,
        telefone: document.getElementById('telefone').value
    };

    try {
        const response = await fetch(`${API_URL}/clientes`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cliente)
        });

        if (response.ok) {
            alert('Cliente cadastrado com sucesso!');
            carregarClientes(); // Atualiza a lista
            e.target.reset(); // Limpa o formulário
        } else {
            alert('Erro ao cadastrar cliente');
        }
    } catch (error) {
        console.error("Erro:", error);
    }
});

// Carrega os dados quando a página é aberta
document.addEventListener('DOMContentLoaded', () => {
    carregarClientes();
});