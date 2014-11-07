/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Certificado;
import co.com.etoc.opline.persistencia.entidades.Pago;
import java.util.Date;
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
    
    List<Certificado> consultarPorEstadoPendiente();
   
    List<Asociado> consultarAs(List<Certificado> lista); 
    
   void actualizar(long id, String respuesta);
    
    
    List<Certificado> consultarPorEmpleado(Integer idEmpleado);
    
    Asociado consultarNom(Certificado certi);
    
    void insertarPago(long id, long num, Date fechaPago, double valor ,String ob);
    
    public void peticion(String tablaReferencia, String documentoSolicitante,Date fecha,String respuesta);
    
  
    
    List<Certificado> listarAprobados();
    
    List<Certificado> listarRechazados();
    
}
