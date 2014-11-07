/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Certificado;
import co.com.etoc.opline.persistencia.entidades.Pago;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class CertificadoFacade extends AbstractFacade<Certificado> implements CertificadoFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CertificadoFacade() {
        super(Certificado.class);
    }

    @Override
    public List<Certificado> consultarPorEmpleado(Integer idEmpleado) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from certificado where id_empleado = "+idEmpleado, Certificado.class);
        } catch (Exception e) {
        }
        return q.getResultList();
    }

    @Override
    public void peticion(String tablaReferencia, String documentoSolicitante,Date fecha,String respuesta) {
        Query q = null;
        try {
            int id = 2;
          
           
            DateFormat d = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
          
            q = em.createNativeQuery("INSERT INTO certificado (id_empleado,tabla_referencia,documento_solicitante,fecha_solicitud,respuesta) VALUES (?,?,?,?,?)");
            q.setParameter(1, id);
            q.setParameter(2, tablaReferencia);
            q.setParameter(3, documentoSolicitante);
            q.setParameter(4, fecha);
            q.setParameter(5, respuesta);
            q.executeUpdate();
            System.out.println("Inserto");
        } catch (Exception e) {
            System.out.println("Se presento un problema realizando la petición de un certificado"+e.getMessage());
        }
        
        
    }

  

    @Override
    public List<Certificado> consultarPorEstadoPendiente() {
        Query q = null;
        try {
            q = em.createNativeQuery("SELECT id_certificado,documento_solicitante FROM certificado WHERE respuesta = 'pendiente'",Certificado.class);
            System.out.println(q.getResultList());
        } catch (Exception e) {
            System.out.println("Se presento un error listando por estado penditente Certificado"+ e.getMessage());
        }
        return  q.getResultList();
    }


   

    @Override
    public List<Certificado> listarAprobados() {
       Query q = null;
        try {
            q = em.createNativeQuery("SELECT * FROM certificado WHERE respuesta='aprobado' ORDER BY fecha_respuesta desc",Certificado.class);
        } catch (Exception e) {
            System.out.println("Se presento un error"+e.getMessage());
        }
        return q.getResultList();
    }

    @Override
    public List<Certificado> listarRechazados() {
         Query q = null;
        try {
            q = em.createNativeQuery("SELECT * FROM certificado WHERE respuesta='rechazado' ORDER BY fecha_respuesta desc ",Certificado.class);
        } catch (Exception e) {
            System.out.println("Se presento un error re"+e.getMessage());
        }
        return q.getResultList();
    }

    @Override
    public void actualizar(long id ,String respuesta) {
        Query q = null;
        try {
            q = em.createNativeQuery("Update certificado set fecha_respuesta = now() ,"
                    + " respuesta =? WHERE id_certificado = ?");
            q.setParameter(1, respuesta);
            q.setParameter(2, id);
            q.executeUpdate();
            System.out.println("Actualice");
        } catch (Exception e) {
            System.out.println("Se presento un problema actualizando el estado "+ e.getMessage());
        }
    }

    @Override
    public List<Asociado> consultarAs(List<Certificado> lista) {
       Query q = null;
       List<Asociado> asociado = null;
       try{
           for (int i = 0; i < lista.size(); i++) {
               q = em.createNativeQuery("SELECT * FROM asociado WHERE cedula = ?",Asociado.class);
               q.setParameter(1, lista.get(i));
               asociado.add((Asociado) q.getSingleResult());
               System.out.println("Entro");
           }
       }catch(Exception e){
           System.out.println("Error no entro "+e);
       }
       return  asociado;
    }

    @Override
    public Asociado consultarNom(Certificado certi) {
       Query q = null;
        try {
            q = em.createNativeQuery("SELECT * FROM asociado WHERE cedula = ?",Asociado.class);
            q.setParameter(1, certi.getDocumentoSolicitante());
        } catch (Exception e) {
            System.out.println("Error consultarNom"+e.getMessage());
        }
        return (Asociado) q.getSingleResult();
    }

    @Override
    public void insertarPago(long id, long num, Date fechaPago,double valor ,String ob) {
       Query q = null;
        try {
            q = em.createNativeQuery("INSER INTO pago (id_tipo_pago,numero_recibo,id_asociado,fecha_pago,valor_pago,observacion) VALUES(3,?,?,?,?,?)");
            q.setParameter(1, num);
            q.setParameter(2, id);
            q.setParameter(3, fechaPago);
            q.setParameter(4, valor);
            q.setParameter(5, ob);
            System.out.println("Inserto ");
        } catch (Exception e) {
            System.out.println("DAO "+e.getMessage());
        }
    }

    

    

}
