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
public class ControladorContato implements Serializable {

    private Contato contato = new Contato();
    private final ContatosDao dao = new ContatosJDBC();

    public void salvar() {
        if (contato.getId() != 0){
            dao.editar(contato);
        } else {
            dao.salvar(contato);
        }
        limparCampos();
    }

    public void excluir(Contato ContatoParaExcluir) {
        dao.excluir(ContatoParaExcluir);
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
    
    public Contato localizar(String nome){
        return dao.localizarPor(nome);
    }
    
    private void limparCampos() {
        contato.setId(0);
        contato.setNome("");
        contato.setCpf("");
    }
}
