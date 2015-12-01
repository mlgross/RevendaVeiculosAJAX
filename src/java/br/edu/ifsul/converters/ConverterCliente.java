package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Cliente;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mlgross
 */
@FacesConverter(value = "converterCliente")
public class ConverterCliente implements Converter, Serializable {

    @PersistenceContext(unitName = "Revenda-WebPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null) {
            return null;
        }
        return em.find(Cliente.class, string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Cliente obj = (Cliente) o;
        return obj.getCpf();  
    }

}
