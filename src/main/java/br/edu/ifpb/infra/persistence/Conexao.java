package br.edu.ifpb.infra.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;

/**
 *
 * @author alexalins
 */
public class Conexao {
    
    private Connection connection;

    public Connection init() {
        try {
            Class.forName("org.postgresql.Driver");
            return connection = DriverManager.getConnection("jdbc:postgresql://host-banco:5432/dac-jsf", "postgres", "123");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public  static void fecharConexao(Connection c) throws SQLException{
        c.close();
    }
}
