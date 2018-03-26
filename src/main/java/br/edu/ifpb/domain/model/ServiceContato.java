package br.edu.ifpb.domain.model;

import br.edu.ifpb.infra.persistence.ContatosJDBC;
import java.util.List;

/**
 *
 * @author alexalins
 */
public class ServiceContato {
    public Contatos dao = new ContatosJDBC();
    
    public void salvar(Contato contato){
        dao.salvar(contato);
    }
    
    public List<Contato> todosOsContatos(){
        return dao.listarTodos();
    }
    
    public void excluir (Contato contato){
        dao.excluir(contato);
    }
    
    public Contato localizarPor(String nome){
        return dao.localizarPor(nome);
    }
}
