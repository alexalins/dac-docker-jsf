package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.model.Contato;
import br.edu.ifpb.domain.model.ServiceContato;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author alexalins
 */
@Named
@RequestScoped
public class ControladorContato {
    
    private Contato contato = new Contato();
    private final ServiceContato service =  new ServiceContato();
    
    public String salvar(Contato contato) {
        this.service.salvar(contato);
        return null;
    }

    public String excluir(Contato contatoParaExcluir) {
        this.service.excluir(contatoParaExcluir);
        return null;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> todosOsContatos() {
        return this.service.todosOsContatos();
    }    

}
