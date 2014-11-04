/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Pago;
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
public class PagoFacade extends AbstractFacade<Pago> implements PagoFacadeLocal {

    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoFacade() {
        super(Pago.class);
    }

    @Override
    public List<Pago> tipoExclusivo(Integer listarPor) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where id_tipo_pago = ?", Pago.class);            
            q.setParameter(1, listarPor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return q.getResultList();
    }
    
    @Override
    public List<Pago> listarOrdenadamente(){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago order by numero_recibo desc", Pago.class);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("Se ha producido un error en el Facade al tratar de listar Ordenadmente.");
            return null;
        }
        
    }

    @Override
    public Pago ultimoPago(Integer idAsociado) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where id_asociado = "+idAsociado+" order by fecha_pago desc limit 1 ", Pago.class);
        } catch (Exception e) {
        }
        return (Pago)q.getSingleResult();
    }

    @Override
    public Pago ultimoPago(Integer idAsociado, Integer idTipoPago) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where id_asociado = "+idAsociado+" and id_tipo_pago = "+idTipoPago+" order by fecha_pago desc limit 1 ", Pago.class);
             return (Pago)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
       
    }
    
    

}
