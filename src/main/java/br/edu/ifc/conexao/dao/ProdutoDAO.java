/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.conexao.dao;

import br.edu.ifc.conexao.db.DatabaseConnection;
import br.edu.ifc.conexao.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class ProdutoDAO implements GenericDAO<Produto, Long> {

    private final String SQL_INSERT = "INSERT INTO PRODUTOS(nome, valor) VALUES (?,?)";
    private final String SQL_UPDATE = "UPDATE PRODUTOS SET NOME = ?, VALOR = ? WHERE ID =? ";
    private final String SQL_DELETE = "DELETE FROM PRODUTOS WHERE ID = ?";
    private final String SQL_SELECT = "SELECT * FROM PRODUTOS";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM PRODUTOS WHERE ID = ? ";

    @Override
    public boolean salvar(Produto objeto) throws SQLException {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, objeto.getNome());
            ps.setDouble(2, objeto.getValor());
            boolean resultado = ps.executeUpdate() == 1;
            DatabaseConnection.commit();
            return resultado;
        } catch (SQLException ex) {
            DatabaseConnection.rollback();
            throw ex;
        } finally {
            DatabaseConnection.fecharConexao();
        }
    }

    @Override
    public boolean atualizar(Produto objeto, Long id) throws SQLException {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, objeto.getNome());
            ps.setDouble(2, objeto.getValor());
            ps.setLong(3, id);
            boolean resultado = ps.executeUpdate() == 1;
            DatabaseConnection.commit();
            return resultado;
        } catch (SQLException ex) {
            DatabaseConnection.rollback();
            throw ex;
        } finally {
            DatabaseConnection.fecharConexao();
        }
    }

    @Override
    public boolean excluir(Long id) throws SQLException {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE);

            ps.setLong(1, id);
            boolean resultado = ps.executeUpdate() == 1;
            DatabaseConnection.commit();
            return resultado;
        } catch (SQLException ex) {
            DatabaseConnection.rollback();
            throw ex;
        } finally {
            DatabaseConnection.fecharConexao();
        }
    }

    @Override
    public List<Produto> listarTodos() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = new Produto(rs.getLong("id"), rs.getString("nome"), rs.getDouble("valor"));
                lista.add(p);

            }
            DatabaseConnection.commit();
            return lista;

        } catch (SQLException ex) {

            DatabaseConnection.rollback();
            throw ex;
        } finally {

            DatabaseConnection.fecharConexao();
        }
       
    }

    @Override
    public Produto getPorId(Long id) throws SQLException {
        Produto p = null;
        ResultSet rs;
        try {
            PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(SQL_SELECT_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Produto();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getDouble("valor"));
                // teste
            }
            DatabaseConnection.commit();
            return p;

        } catch (SQLException ex) {

            DatabaseConnection.rollback();

        } finally {

            DatabaseConnection.fecharConexao();
        }
        return p;
    }

}
