/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.projeto.sistema.model;

import java.util.Date;

/**
 *
 * @author aluno
 */
public class Cliente {

    private Long id;
    private String nome;
    private Date dtnascimento;
    private String telefone;
    private String endereco;
    private Boolean status;

    public Cliente(Long id, String nome, Date dtnascimento, String telefone, String endereco, Boolean status) {
        this.id = id;
        this.nome = nome;
        this.dtnascimento = dtnascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.status = status;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(Date dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

   

    
    
    
    
}
