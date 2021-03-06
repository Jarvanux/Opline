/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Convenio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ConvenioFacadeLocal {

    void create(Convenio convenio);

    void edit(Convenio convenio);

    void remove(Convenio convenio);

    Convenio find(Object id);

    List<Convenio> findAll();

    List<Convenio> findRange(int[] range);

    int count();
    
    Integer ConsultarNumeroVehiculos(Integer idConvenio);
}
