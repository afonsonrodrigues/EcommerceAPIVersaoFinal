package org.serratec.dto.cliente;

import java.time.LocalDate;

import org.serratec.models.Cliente;
import org.serratec.models.Endereco;

public class ClienteCompletoDTO {

    private String email;
    private String username;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private Boolean statusConta;

    public ClienteCompletoDTO(Cliente cliente) {
        this.email = cliente.getEmail();
        this.username = cliente.getUserNameCliente();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCPF();
        this.telefone = cliente.getTelefone();
        this.dataNascimento = cliente.getDataNascimento();
        this.endereco = cliente.getEndereco();
//        this.endereco = new EnderecoCompletoDTO(cliente.getEndereco());
        this.statusConta = cliente.getStatusConta();
    }

    public Boolean getStatusConta() {
        return statusConta;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

}