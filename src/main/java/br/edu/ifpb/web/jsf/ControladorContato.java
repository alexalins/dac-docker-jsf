package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.model.Contato;
import br.edu.ifpb.domain.model.ContatosDao;
import br.edu.ifpb.infra.persistence.ContatosJDBC;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author alexalins
 */
@Named
@RequestScoped
public class ControladorContato {

    private Contato contato = new Contato();
    private final ContatosDao dao = new ContatosJDBC();
    private List<Contato> contatos = new ArrayList<>();
    private String buscar = null;

    public ControladorContato() {
        contatos = dao.listarTodos();
    }

    public void salvar() {
        if (contato.getId() != 0) {
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
        if (contatos.isEmpty()) {
            return dao.listarTodos();
        }
        return contatos;
    }

    public void BuscarFilter(AjaxBehaviorEvent evento) {
        this.contatos = contatos.stream()
                .filter(c -> c.getNome()
                .toLowerCase()
                .startsWith(buscar.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void limparCampos() {
        contato.setId(0);
        contato.setNome("");
        contato.setCpf("");
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
}
