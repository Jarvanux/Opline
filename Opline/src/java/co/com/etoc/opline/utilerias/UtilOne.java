package co.com.etoc.opline.utilerias;

import java.net.Socket;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilOne {
    
    public static String md5(String clave) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clave.getBytes());
        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        //algoritmo y arreglo md5
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0" + Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        //clave encriptada
        return h.toString();
    }

    
    public static boolean validarConexion(){
        String pagina = "www.google.com";
        Integer puerto = 80;
        try {
            Socket s = new Socket(pagina, puerto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}