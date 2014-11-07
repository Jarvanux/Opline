/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.CertificadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.PagoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Certificado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author andres
 */
@ManagedBean
@ViewScoped
public class ConsultarCertificadoManagedBean {

    
    private List<Certificado> listarCertificado;
    private List<Certificado> filtrarCertificado;
    
    
    private String listarPor;
    private Certificado dato;
    private List<Asociado> idAsociado;
    private List<Asociado> asociado;
    private Asociado a;
    
    private long idAsoci;
    private long numero;
    private Date fechaPago;
    private double valor;
    private String obser;
    
    
    public ConsultarCertificadoManagedBean() {
        
    }
    
    @EJB
    private CertificadoFacadeLocal localCertificado;
    
    @EJB
    private PagoFacadeLocal localPago;
    
    
    
    @PostConstruct
    public void init(){
    
        listarCertificado = localCertificado.listarAprobados();
        if(listarCertificado.size() != 0){
            dato = listarCertificado.get(0);
        }else {
            listarCertificado =  localCertificado.listarRechazados();
            dato = listarCertificado.get(0);
        }
        this.listarPor = "aprobado";
        
        this.listarPorPreferencia();
        
         idAsociado = new ArrayList<Asociado>();
        
    }
    
   
    
    
    public String listarPorPreferencia(){
        try {
            if (listarPor.equals("aprobado")) {
                listarCertificado = localCertificado.listarAprobados();
            } else {
                listarCertificado = localCertificado.listarRechazados();
            }
        } catch (Exception e) {
            System.out.println("Se presento un error listando por preferencia " 
            + e.getMessage());
        }
        return "consultarCertificado.xhtml";
    }
    

    
    public String registrar(){
        String mensaje;
        try {
            localCertificado.insertarPago(idAsoci, numero, fechaPago, valor, obser);
            mensaje="Inserto";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            mensaje="No inserto";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
    return  "consultarCertificado.xhtml";
    }
    
    public void prepararDatos(Certificado certi){
        this.dato = certi;
        consultarN(dato);
    }
    
    public void consultarN(Certificado certi){
     a = localCertificado.consultarNom(certi);
    }
    

    public List<Certificado> getListarCertificado() {
        return listarCertificado;
    }

    public void setListarCertificado(List<Certificado> listarCertificado) {
        this.listarCertificado = listarCertificado;
    }

    public List<Certificado> getFiltrarCertificado() {
        return filtrarCertificado;
    }

    public void setFiltrarCertificado(List<Certificado> filtrarCertificado) {
        this.filtrarCertificado = filtrarCertificado;
    }

    public String getListarPor() {
        return listarPor;
    }

    public void setListarPor(String ListarPor) {
        this.listarPor = ListarPor;
    }

    public Certificado getDato() {
        return dato;
    }

    public void setDato(Certificado dato) {
        this.dato = dato;
    }

    public List<Asociado> getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(List<Asociado> idAsociado) {
        this.idAsociado = idAsociado;
    }

    public List<Asociado> getAsociado() {
        return asociado;
    }

    public void setAsociado(List<Asociado> asociado) {
        this.asociado = asociado;
    }

    public Asociado getA() {
        return a;
    }

    public void setA(Asociado a) {
        this.a = a;
    }

    public long getIdAsoci() {
        return idAsoci;
    }

    public void setIdAsoci(long idAsoci) {
        this.idAsoci = idAsoci;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObser() {
        return obser;
    }

    public void setObser(String obser) {
        this.obser = obser;
    }

   

    
    
    
    
}
