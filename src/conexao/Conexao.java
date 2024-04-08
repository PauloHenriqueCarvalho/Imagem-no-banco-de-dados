/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author paulo
 */
public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/bancoComImagem";
    private static final String nome = "root";
    private static final String senha = "1234";
    
    public static Connection conectar() throws SQLException {
      return (Connection)DriverManager.getConnection(url, nome, senha);
    }
}
