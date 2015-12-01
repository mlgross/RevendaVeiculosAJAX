package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author mlgross 
 */
@ManagedBean(name = "controleCarro")
@SessionScoped
public class ControleCarro implements Serializable{

    @EJB
    private CarroDAO dao;
    private Carro objeto;
    
    public ControleCarro() {
    }
    
    public String listar() {
        return "/privado/carro/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Carro();
    }

    public void salvar() {
        try {
            if (objeto.getRenavan()== null) {
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

    public void editar(Integer renavan) {
        try {
            objeto = dao.getObjectById(renavan);

        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+e.getMessage());

        }
    }
    
    public void excluir(Integer renavan){
        try {
            objeto = dao.getObjectById(renavan);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto:"+Util.getMensagemErro(e));
        }
    }    

    public CarroDAO getDao() {
        return dao;
    }

    public void setDao(CarroDAO dao) {
        this.dao = dao;
    }

    public Carro getObjeto() {
        return objeto;
    }

    public void setObjeto(Carro objeto) {
        this.objeto = objeto;
    }
    
}
