package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.AsociadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.CertificadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.PagoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Certificado;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.EstadoCertificado;
import co.com.etoc.opline.persistencia.entidades.Pago;
import co.com.etoc.opline.persistencia.entidades.TipoPago;
import java.io.Serializable;
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
 * @author jhonjaider1000
 */
@ManagedBean
@ViewScoped
public class ConsultarCertificadosManaged extends ValidaSesion implements Serializable {

    //Creamos las listas que controla la tabla de la vista.
    private List<Certificado> listaCertificados;
    private List<Certificado> filtroCertificados;
    private List<EstadoCertificado> listaEstadoCertificado;

    //Atributos 
    private String filtrarPor;

    //Atributos BD.    
    private Integer numeroRecibo;
    private Asociado idAsociado;
    private TipoPago idTipoPago;
    private Empleado idEmpleado;
    private Pago idPago;
    private Certificado idCertificado;
    private Date fechaPago;
    private Double valorPago;
    private String observacion;

    public ConsultarCertificadosManaged() {
    }

    public void instanciarListarPor() {
        this.listaEstadoCertificado = new ArrayList<EstadoCertificado>();
        this.listaEstadoCertificado.add(new EstadoCertificado("aprobado", "Aprobados"));
        this.listaEstadoCertificado.add(new EstadoCertificado("rechazado", "Rechazados"));
        this.listaEstadoCertificado.add(new EstadoCertificado("pendiente", "Pendientes"));
    }
    @EJB
    private CertificadoFacadeLocal certificadoFL;

    @EJB
    private AsociadoFacadeLocal asociadoFL;

    @EJB
    private PagoFacadeLocal pagoFL;

    @PostConstruct
    //Primer método que se ejecutará automaticamente cuando se llame el managed.   
    public void init() {
        this.instanciarListarPor();
        this.filtrarPor = "aprobado";
        this.listarPor();
    }

    /**
     * Registrar el pago.
     */
    public void registrarPago() {
        try {
            //El objeto que creamos de la clase empleado, recibe el objeto
            //que viene de la sesión, pues esta clase ha heredado de ValidaSesion
            //y por tal motivo puede acceder a ese objeto que guarda la información
            //del usuario que se ha logeado en el sistema.
            this.idEmpleado = empleado;
            this.idTipoPago = new TipoPago(3); //Se asigna el tipo de pago certificado.            
            Pago p = new Pago();
            p.setNumeroRecibo(numeroRecibo);
            p.setIdAsociado(idAsociado);
            p.setIdEmpleado(idEmpleado);
            p.setIdTipoPago(idTipoPago);
            p.setValorPago(valorPago);
            p.setFechaPago(new Date()); //Se registra la fecha del sistema.
            pagoFL.create(p); //Se regustra el pago en l BD.
            //Se iforma en la vista lo sucedido en la transacción.

            //Se inserta el id del pago en el registro de certificado.
            this.idCertificado.setIdPago(numeroRecibo);
            this.certificadoFL.edit(idCertificado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Pago registrado."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se pudo registrar el pago"));
        }
    }    

    public void consultarAsociado(Certificado certificado) {
        this.idAsociado = asociadoFL.consultarAsosicado(certificado.getDocumentoSolicitante());
        this.idCertificado = certificado;
        if (idCertificado.getIdPago() != null) {
            this.idPago = pagoFL.consultarPorId(idCertificado.getIdPago());
        }
    }

    public void listarPor() {
        this.listaCertificados = certificadoFL.listarPor(this.filtrarPor);
    }

    //Métodos set y get.
    public List<Certificado> getListaCertificados() {
        return listaCertificados;
    }

    public void setListaCertificados(List<Certificado> listaCertificados) {
        this.listaCertificados
                = listaCertificados;
    }

    public List<Certificado> getFiltroCertificados() {
        return filtroCertificados;
    }

    public void setFiltroCertificados(List<Certificado> filtroCertificados) {
        this.filtroCertificados = filtroCertificados;
    }

    public String getFiltrarPor() {
        return filtrarPor;
    }

    public void setFiltrarPor(String filtrarPor) {
        this.filtrarPor = filtrarPor;
    }

    public List<EstadoCertificado> getListaEstadoCertificado() {
        return listaEstadoCertificado;
    }

    public void setListaEstadoCertificado(List<EstadoCertificado> listaEstadoCertificado) {
        this.listaEstadoCertificado = listaEstadoCertificado;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public Asociado getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(Asociado idAsociado) {
        this.idAsociado = idAsociado;
    }

    public TipoPago getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(TipoPago idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Certificado getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(Certificado idCertificado) {
        this.idCertificado = idCertificado;
    }

    public Pago getIdPago() {
        return idPago;
    }

    public void setIdPago(Pago idPago) {
        this.idPago = idPago;
    }

}
