package br.edu.ifsul.controle;

import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ifsul.dao.ReparadorDAO;
import br.edu.ifsul.modelo.Reparador;

/**
 *
 * @author mlgross
 */
@ManagedBean(name = "controleReparador")
@SessionScoped
public class ControleReparador implements Serializable{
    
    @EJB
    private ReparadorDAO dao;
    private Reparador objeto;

    public ControleReparador() {
    }
    
    public String listar() {
        return "/privado/reparador/listar?faces-redirect=true";
    }    

    public void novo() {
        objeto = new Reparador();
    }

    public void salvar() {
        try {
            if (objeto.getPis()== null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + e.getMessage());
        }
    }

    public void cancelar() {
        objeto = null;
    }

    public void editar(Integer pis) {
        try {
            objeto = dao.getObjectById(pis);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+e.getMessage());
        }
    }
    
    public void excluir(Integer pis){
        try {
            objeto = dao.getObjectById(pis);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto:"+Util.getMensagemErro(e));
        }
    }

    public ReparadorDAO getDao() {
        return dao;
    }

    public void setDao(ReparadorDAO dao) {
        this.dao = dao;
    }

    public Reparador getObjeto() {
        return objeto;
    }

    public void setObjeto(Reparador objeto) {
        this.objeto = objeto;
    }
    
}