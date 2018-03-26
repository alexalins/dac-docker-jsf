package br.edu.ifpb.infra.persistence;

import br.edu.ifpb.domain.model.Contato;
import br.edu.ifpb.domain.model.Contatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

/**
 *
 * @author alexalins
 */
public class ContatosJDBC implements Contatos {

    private final Connection conexao;

    public ContatosJDBC() {
        this.conexao = Conexao.getConnection();
    }

    @Override
    public void excluir(Contato contatoParaExcluir) {
       try {
            String sql = "DELETE FROM contato WHERE CPF = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1,contatoParaExcluir.getCpf());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Contato> listarTodos() {
        try {
            String sql = "SELECT * FROM contato";

            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Contato> contatos = new ArrayList<>();
            while (rs.next()) {
                Contato c = new Contato();
                c.setCpf(rs.getString("cpf"));
                c.setNome(rs.getString("nome"));

                contatos.add(c);
            }
            return contatos;
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void salvar(Contato contatoParaSalvar) {
        try {
            String sql = "INSERT INTO contato(nome, cpf) VALUES (?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, contatoParaSalvar.getNome());
            stmt.setString(2, contatoParaSalvar.getCpf());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Contato localizarPor(String nome) {
        try {
            String sql = "SELECT * FROM contato WHERE nome = " + nome;
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Contato c = new Contato();
            while (rs.next()) {
                c.setCpf(rs.getString("cpf"));
                c.setNome(rs.getString("nome"));
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void editar(Contato contatoParaEditar){
    try {
            String sql = "UPDATE FROM contato SET NOME = ?, CPF = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1,contatoParaEditar.getNome());
            stmt.setString(1,contatoParaEditar.getCpf());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
