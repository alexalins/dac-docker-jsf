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

/**
 *
 * @author alexalins
 */
public class ContatosJDBC implements Contatos {

    private final Conexao conexao;

    public ContatosJDBC() {
        conexao = new Conexao();
    }

    @Override
    public void excluir(Contato contatoParaExcluir) {
       try {
            String sql = "DELETE FROM contato WHERE CPF = ?";
            PreparedStatement stmt = conexao.init().prepareStatement(sql);
            stmt.setString(1,contatoParaExcluir.getCpf());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Contato> listarTodos() {
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM contato";
            stmt = conexao.init().prepareStatement(sql);
            return criarContato(stmt);
            
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.emptyList();
    }

    @Override
    public void salvar(Contato contatoParaSalvar) {
        String sql = "INSERT INTO contato(nome, cpf) VALUES (?,?)";
         PreparedStatement stmt = null;
        try {
            stmt = conexao.init().prepareStatement(sql);

            stmt.setString(1, contatoParaSalvar.getNome());
            stmt.setString(2, contatoParaSalvar.getCpf());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Contato localizarPor(String nome) {
        String consulta = "SELECT * FROM contato WHERE nome=?";


        PreparedStatement stmt = null;
        try {
            stmt = conexao.init().prepareStatement(consulta);
            stmt.setString(1, nome);
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return criarContato(stmt).get(0);
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
    
    @Override
    public void editar(Contato contatoParaEditar){
    try {
            String sql = "UPDATE FROM contato SET NOME = ?, CPF = ?";
            PreparedStatement stmt = conexao.init().prepareStatement(sql);
            stmt.setString(1,contatoParaEditar.getNome());
            stmt.setString(1,contatoParaEditar.getCpf());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
