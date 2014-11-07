/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.AsociadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.CertificadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.PagoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Certificado;
import co.com.etoc.opline.persistencia.entidades.Pago;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author andres
 */
@ManagedBean
@RequestScoped
public class CalificarCertificadoManaged {

    private List<Certificado> listaCertificado;

    private List<Certificado> filtartificado;
    private List<Pago> listaPagos;

    private String respuesta;
    private Certificado dato;
    private Integer listarPor;
    private Asociado asociadoSelect;

    @EJB
    private CertificadoFacadeLocal localCertificado;

    @EJB
    private PagoFacadeLocal localPago;

    @EJB
    private AsociadoFacadeLocal localAsociado;

    public CalificarCertificadoManaged() {
    }

    @PostConstruct
    public void init() {

        listaCertificado = localCertificado.consultarPorEstadoPendiente();
        listaPagos = localPago.listarOrdenadamente();
    }

    public void listarAsociado() {

        localCertificado.consultarPorEstadoPendiente();

    }

    public void consultarAsocidado(String documento) {
        try {
            System.out.println("El documento recibido fue: "+documento);
            this.asociadoSelect = localAsociado.consultarAsosicado(respuesta);
            System.out.println("El asociado se llama: "+asociadoSelect.getApellido());
        } catch (Exception e) {
            System.out.println("Error CalificarCertificadoManaged.consultarAsociad()");
        }
    }

    public String aprobar(long certificado) {
        String mensaje = null;
        long cer = 0;
        try {
            cer = certificado;
            System.out.println(certificado);
            respuesta = "aprobado";
            localCertificado.actualizar(cer, respuesta);
            mensaje = "Certificado aprobado";
        } catch (Exception e) {
            System.out.println("A ocurrido algun error" + e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));

        return "calificarCertificado.xhtml";
    }

    public String rechazar(long certificado) {
        String mensaje = null;
        long cer = 0;
        try {
            cer = certificado;
            System.out.println(certificado);
            respuesta = "rechazado";
            localCertificado.actualizar(cer, respuesta);
            mensaje = "Certificado rechazado";

        } catch (Exception e) {
            System.out.println("A ocurrido algun error");
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
        return "calificarCertificado.xhtml";
    }

    public void prepararDatos(Certificado certi) throws IOException {
        this.dato = certi;

    }

    public void listarPago() {
        if (listarPor > 0) {
            this.listaPagos = localPago.tipoExclusivo(listarPor);
        } else {
            this.listaPagos = localPago.listarOrdenadamente();
        }
    }

//   
//    public void consultarDocumento(String documento) {
//       
//        try {
//           localAsociado.consultarPagos(documento);
//           long id = localAsociado.consultarPagos(documento);
//                    
//            if( id > 0){
//                listaPagos = localPago.ultimosPagos(id);
//            }
//        } catch (Exception e) {
//            System.out.println("A ocurrido uun error");
//        }
//       
//    }
    public List<Certificado> getListaCertificado() {
        return listaCertificado;
    }

    public void setListaCertificado(List<Certificado> listaCertificado) {
        this.listaCertificado = listaCertificado;
    }

    public List<Certificado> getFiltartificado() {
        return filtartificado;
    }

    public void setFiltartificado(List<Certificado> filtartificado) {
        this.filtartificado = filtartificado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<Pago> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(List<Pago> listaPagos) {
        this.listaPagos = listaPagos;
    }

    public Certificado getDato() {
        return dato;
    }

    public void setDato(Certificado dato) {
        this.dato = dato;
    }

    public Integer getListarPor() {
        return listarPor;
    }

    public void setListarPor(Integer listarPor) {
        this.listarPor = listarPor;
    }

    public Asociado getAsociadoSelect() {
        return asociadoSelect;
    }

    public void setAsociadoSelect(Asociado asociadoSelect) {
        this.asociadoSelect = asociadoSelect;
    }

}
