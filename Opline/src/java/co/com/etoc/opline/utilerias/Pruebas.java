
/**
 *
 * @author jhonjaider1000
 */
package co.com.etoc.opline.utilerias;

import java.util.Date;
import java.util.Scanner;

class Pruebas {

    public static void main(String[] args) throws Exception {
        //Espacio para crear pruebas. :D
//        System.out.println("Antiguedad: "+UtilOne.diferencia(new Date()));
        Scanner lea = new Scanner(System.in);
        System.out.println("Clave a encriptar:");
        String clave = lea.nextLine();
        System.out.println("Clave encriptada: "+UtilOne.md5(clave));
        
    }
}
