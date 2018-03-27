package br.edu.ifpb.domain.model;

import java.util.List;

/**
 *
 * @author alexalins
 */
public interface Contatos {
    public void excluir(Contato contatoParaExcluir);

    public List<Contato> listarTodos();

    public void salvar(Contato contatoParaSalvar);

    public Contato localizarPor(String nome);
    
    public void editar(Contato contatoParaEditar);
}
