/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.ConductorFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Conductor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Juan Camilo
 */
@ManagedBean
@ViewScoped
public class ConductoresManaged implements Serializable {

    //Trae la lista de los conductores con el primer List, el segundo sirve para el filtro
    private List<Conductor> listaConductores;
    private List<Conductor> filtroConductores;
    
    //Objeto conductor para utilizar en el método editar
    private Conductor conductorSelecionado;
    
    @EJB
    ConductorFacadeLocal conductorFLocal;
    
    public void editar() {
        String mensaje;
        try {
            conductorFLocal.edit(conductorSelecionado);
            mensaje = "Conductor actualizado con éxito";
        } catch (Exception e) {
            mensaje = "Se presentó un error al actualizar el conductor, \n"
                    + " por favor revise los campos del formulario y asegurese "
                    + " que todos estén diligenciados por completo y correctamente";
        }
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
    }

    public List<Conductor> getListaConductores() {
        return listaConductores;
    }

    public void setListaConductores(List<Conductor> listaConductores) {
        this.listaConductores = listaConductores;
    }

    public List<Conductor> getFiltroConductores() {
        return filtroConductores;
    }

    public void setFiltroConductores(List<Conductor> filtroConductores) {
        this.filtroConductores = filtroConductores;
    }

    public Conductor getConductorSelecionado() {
        return conductorSelecionado;
    }

    public void setConductorSelecionado(Conductor conductorSelecionado) {
        this.conductorSelecionado = conductorSelecionado;
    }
    
    
}
