/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.PagoConvenio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class PagoConvenioFacade extends AbstractFacade<PagoConvenio> implements PagoConvenioFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoConvenioFacade() {
        super(PagoConvenio.class);
    }

    @Override
    public List<PagoConvenio> listarPor(Integer idConvenio) {
        Query q = null;
        try{
            q = em.createNativeQuery("SELECT * FROM pago_convenio where id_convenio = ?", PagoConvenio.class);
            q.setParameter(1, idConvenio);
        }catch(Exception e){
            System.out.println("Error PagoConvenioFacade al listarPor");
        }
        return q.getResultList();
    }
    @Override
    public List<PagoConvenio> listarOrdenadamente(){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago_convenio order by numero_consig desc", PagoConvenio.class);
        } catch (Exception e) {
            System.out.println("Se ha producido un error en el Facade al tratar de listar Ordenadmente.");
        }
        return q.getResultList();
    }
    
    
     @Override
    public PagoConvenio ultimoPago(Integer idVehiculo) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago_convenio where id_vehiculo = "+idVehiculo+" order by fecha_consignacion desc limit 1", PagoConvenio.class);
            return (PagoConvenio)q.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }
    
    @Override
    public PagoConvenio ultimoPago(Integer idVehiculo, Integer idConvenio) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago_convenio where id_vehiculo = "+idVehiculo+" and id_convenio = "+idConvenio+" order by fecha_consignacion desc limit 1", PagoConvenio.class);         
            return (PagoConvenio)q.getSingleResult();
        } catch (Exception e) {
            return null;
       }
        
    }
    
    
}
