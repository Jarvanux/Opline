/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Certificado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface CertificadoFacadeLocal {

    void create(Certificado certificado);

    void edit(Certificado certificado);

    void remove(Certificado certificado);

    Certificado find(Object id);

    List<Certificado> findAll();

    List<Certificado> findRange(int[] range);

    int count();
    
    List<Certificado> consultarPorEmpleado(Integer idEmpleado);

    public boolean comprobarDocumentoRepetido(String cedula);
    
}
