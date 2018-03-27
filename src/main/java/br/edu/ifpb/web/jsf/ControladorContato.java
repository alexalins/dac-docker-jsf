package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.model.Contato;
import br.edu.ifpb.domain.model.ContatosDao;
import br.edu.ifpb.infra.persistence.ContatosJDBC;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author alexalins
 */
@Named
@RequestScoped
public class ControladorContato implements Serializable{
    
    private Contato contato = new Contato();
    private ContatosDao dao =  new ContatosJDBC();
    
    public void salvar() {
       dao.salvar(contato);
    }

    public void excluir() {
        dao.excluir(contato);
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> todosOsContatos() {
        return dao.listarTodos();
    }    
    
    public void editar(){
        dao.editar(contato);
    }
}
