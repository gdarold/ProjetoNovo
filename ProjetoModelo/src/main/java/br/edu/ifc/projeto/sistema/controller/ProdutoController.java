/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.projeto.sistema.controller;

import br.edu.ifc.projeto.sistema.model.Produto;
import br.edu.ifc.projeto.sistema.view.ProdutoGUI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.JInternalFrame;

/**
 *
 * @author fabricio
 */
public class ProdutoController implements ProdutoListener {

    private List<Produto> lista = new ArrayList<>();
    private ProdutoGUI tela;

    public ProdutoController(JInternalFrame tela) {
        this.tela = (ProdutoGUI) tela;

        lista.add(new Produto(1L, "Macarrão",10,"macarrao saboroso", 5.50));
    }

    @Override
    public boolean salvar(Produto produto) {
        boolean salvou = false;
        // Se for produto novo
        if (produto.getId() == null) {
            produto.setId(Long.parseLong(String.valueOf(lista.size() + 1)));
            salvou = lista.add(produto);
        } else {
            // senão, se for uma alteração
            //Remove da lista
            lista.removeIf(new Predicate<Produto>() {
                @Override
                public boolean test(Produto t) {
                    return t.getId().equals(produto.getId());
                }
            });
            // Adiciona na lista
            salvou = lista.add(produto);
        }
        if (salvou) {
            tela.limparTela();
            tela.mostrarMensagem("Produto Salvo com Sucesso!");
            carregar();
        }
        return salvou;
    }

    @Override
    public void carregar() {
        try {
            tela.montarTabela(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean excluir(Produto produto) {
        boolean excluir = lista.remove(produto);

        if (excluir) {
            tela.mostrarMensagem("Produto Excluido com Sucesso!");
            carregar();
        }

        return excluir;
    }

}
