/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.projeto.sistema.util;


import br.edu.ifc.projeto.sistema.model.Cliente;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author fabricio
 */
public class ClienteTabela extends AbstractTableModel {

    private String colunas[] = {"id", "nome", "dtnascimento", "telefone", "endereço", "status", "objeto"};

    private List<?> lista;

    private final int COLUNA_ID = 0;
    private final int COLUNA_NOME = 1;
    private final int COLUNA_DTNASCIMENTO = 2;
    private final int COLUNA_TELEFONE = 3;
    private final int COLUNA_ENDERECO = 4;
    private final int COLUNA_STATUS = 5;
    private final int COLUNA_OBJETO = 6;

    public ClienteTabela(List<?> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int indice) {
        return colunas[indice];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case COLUNA_ID:
                return Long.class;
            case COLUNA_NOME:
                return String.class;
            case COLUNA_DTNASCIMENTO:
                return Date.class;
            case COLUNA_TELEFONE:
                return String.class;
            case COLUNA_ENDERECO:
                return String.class;
            case COLUNA_STATUS:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = (Cliente) this.lista.get(rowIndex);

        switch (columnIndex) {
            case COLUNA_ID:
                return cliente.getId();
            case COLUNA_NOME:
                return cliente.getNome();
            case COLUNA_DTNASCIMENTO:
                return cliente.getDtnascimento();
            case COLUNA_TELEFONE:
                return cliente.getTelefone();
            case COLUNA_ENDERECO:
                return cliente.getEndereco();
            case COLUNA_STATUS:
                return cliente.getStatus();

            case COLUNA_OBJETO:
                return cliente;
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente cliente = (Cliente) this.lista.get(rowIndex);

        switch (columnIndex) {
            case COLUNA_ID:
                cliente.setId(Long.parseLong(aValue.toString()));
                break;
            case COLUNA_NOME:
                cliente.setNome(String.valueOf(aValue));
                break;
            case COLUNA_DTNASCIMENTO:
                cliente.setDtnascimento(converteData(aValue.toString()));
                break;

            case COLUNA_TELEFONE:
                cliente.setTelefone(String.valueOf(aValue));
                break;

            case COLUNA_ENDERECO:
                cliente.setEndereco(String.valueOf(aValue));
                break;
            case COLUNA_OBJETO:
                cliente = (Cliente) aValue;
                break;
        }
        fireTableDataChanged();
    }

    public void escondeColunaObjeto(JTable tabela) {
        int ultimaColuna = tabela.getColumnCount() - 1;
        TableColumn coluna = tabela.getColumnModel().getColumn(ultimaColuna);
        tabela.getColumnModel().removeColumn(coluna);
    }

    public void removerLinha(int linha) {
        lista.remove(linha);
        fireTableDataChanged();
    }

    public void removerLinhas() {
        lista.clear();
        fireTableDataChanged();
    }

    public void setTamanhoColuna(JTable tabela, int coluna, int tamanho) {
        TableColumnModel columnModel = tabela.getColumnModel();
        columnModel.getColumn(coluna).setMaxWidth(tamanho);
    }

    public Date converteData(String dataString) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        if (!(dataString == null)) {
            try {
                date = (Date) formatter.parse(dataString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
}
