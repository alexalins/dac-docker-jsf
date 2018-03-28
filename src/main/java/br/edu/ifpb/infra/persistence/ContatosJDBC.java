package br.edu.ifpb.infra.persistence;

import br.edu.ifpb.domain.model.Contato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.edu.ifpb.domain.model.ContatosDao;

/**
 *
 * @author alexalins
 */
public class ContatosJDBC implements ContatosDao {

    private final Conexao conexao;

    public ContatosJDBC() {
        conexao = new Conexao();
    }

    @Override
    public void excluir(Contato contatoParaExcluir) {
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM contato WHERE id = ?";
            stmt = conexao.init().prepareStatement(sql);
            stmt.setInt(1, contatoParaExcluir.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Contato> listarTodos() {
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM contato order by nome asc";
            stmt = conexao.init().prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return criarContato(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Collections.emptyList();
    }

    @Override
    public void salvar(Contato contatoParaSalvar) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO contato (nome, cpf) VALUES (?,?)";
        try {
            stmt = conexao.init().prepareStatement(sql);

            stmt.setString(1, contatoParaSalvar.getNome());
            stmt.setString(2, contatoParaSalvar.getCpf());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private List<Contato> criarContato(PreparedStatement statement) throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Contato c = new Contato(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("cpf")
            );
            contatos.add(c);
        }

        return contatos;
    }

    @Override
    public void editar(Contato contatoParaEditar) {
        try {
            String sql = "UPDATE contato SET nome = ?, cpf = ? WHERE id = ?";
            PreparedStatement stmt = conexao.init().prepareStatement(sql);
            stmt.setString(1, contatoParaEditar.getNome());
            stmt.setString(2, contatoParaEditar.getCpf());
            stmt.setInt(3, contatoParaEditar.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContatosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
