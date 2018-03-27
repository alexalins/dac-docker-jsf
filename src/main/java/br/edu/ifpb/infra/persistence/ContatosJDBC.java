package br.edu.ifpb.infra.persistence;

import br.edu.ifpb.domain.model.Contato;
import br.edu.ifpb.domain.model.Contatos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexalins
 */
public class ContatosJDBC implements Contatos {

    private Conexao conexao;
    private Contato contato;

    public ContatosJDBC() {
        conexao = new Conexao();
    }

    @Override
    public void excluir(Contato contatoParaExcluir) {
        try {
            String sql = "DELETE FROM contato WHERE cpf=?";
            PreparedStatement statement = conexao.init().prepareStatement(sql);
            statement.setString(1, contatoParaExcluir.getCpf());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Contato> listarTodos() {
        try {
            String consulta = "SELECT * FROM contato";

            PreparedStatement statement = conexao.init().prepareStatement(consulta);
            return criarContato(statement);

        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean salvar(Contato contatoParaSalvar) {
        boolean resultado = false;
        String sql = "INSERT INTO contato (nome,cpf) VALUES(?,?)";
        PreparedStatement statement = null;
        try {
            statement = conexao.init().prepareStatement(sql);

            statement.setString(1, contatoParaSalvar.getNome());
            statement.setString(2, contatoParaSalvar.getCpf());
            if (statement.executeUpdate() > 0) {
                resultado = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public Contato localizarPor(String nome) {
        String consulta = "SELECT * FROM contato WHERE nome=?";


        PreparedStatement statement = null;
        try {
            statement = conexao.init().prepareStatement(consulta);
            statement.setString(1, nome);
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return criarContato(statement).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<Contato> criarContato(PreparedStatement statement) throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Contato c = new Contato(
                    resultSet.getString("nome"),
                    resultSet.getString("cpf")
            );
            contatos.add(c);
        }

        return contatos;
    }
}
