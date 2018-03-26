package br.edu.ifpb.infra.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.enterprise.inject.Produces;

/**
 *
 * @author alexalins
 */
public class Conexao {
    private static final String URL = "jdbc://postgresql:host-database:5432/dac-jsf";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    @Produces
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.err.println();
        }
        return connection;
    }
}
