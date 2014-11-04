/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.negocio.managed;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@Stateless
public class index implements  Serializable{

   public String cargarEmpleados(){
       return "empleado.xhtml";
   }
}
