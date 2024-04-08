/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Conexao;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author paulo
 */
public class ProdutoDAO {

    public boolean create(Produto p, FileInputStream fis, int tamanho) {
        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement ps = conexao.prepareStatement("insert produto (nome, preco, imagem) values (?,?,?)");
            ps.setString(1, p.getNome());
            ps.setFloat(2, p.getPreco());
            ps.setBlob(3, fis, tamanho);

            ps.executeUpdate();
            ps.close();
            conexao.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Produto read(int id) {
        Produto p = new Produto();

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement ps = conexao.prepareStatement("select * from produto where id = ? ");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getFloat("preco"));
                p.setImagem(rs.getBlob("imagem"));
                
            } else{
                JOptionPane.showMessageDialog(null, "Nenhum  produto com esse id!");
            }
            
            rs.close();
            ps.close();         
            conexao.close();
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
