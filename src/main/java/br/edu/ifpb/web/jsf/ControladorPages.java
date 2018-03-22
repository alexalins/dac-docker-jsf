package br.edu.ifpb.web.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author alexalins
 */
@Named
@RequestScoped
public class ControladorPages {
    
    public String adicionar(){
        return "adicionar.xhtml";
    }
    
    public String listar(){
        return "listar.xhtml";
    }
    
    public String voltar(){
        return "index.xhtml";
    }
}
