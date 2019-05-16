package br.edu.ifc.projeto.sistema.controller;

import br.edu.ifc.projeto.sistema.model.Produto;

public interface ProdutoListener {
    
    void carregar();
    boolean salvar(Produto produto);
    boolean excluir(Produto produto);
}
