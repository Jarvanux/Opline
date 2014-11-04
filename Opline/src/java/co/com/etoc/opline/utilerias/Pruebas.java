/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.utilerias;
import co.com.etoc.opline.negocio.managed.EmpleadoManaged;
import co.com.etoc.opline.negocio.managed.ReunionManaged;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.util.Properties;

class Pruebas {
    public static void main(String[] args) throws Exception{
//        System.out.println("Sending mail...");
//        Properties props = new Properties();
////                propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
////        propiedades.setProperty("mail.smtp.starttls.enable", "true");
////        propiedades.setProperty("mail.smtp.port", "587");
////        propiedades.setProperty("mail.smtp.auth", "true");
//        props.setProperty("mail.transport.protocol", "smtp");
//        props.setProperty("mail.host", "smtp.gmail.com");
//        props.setProperty("mail.user", "jjvanegas67@misena.edu.co");
//        props.setProperty("mail.password", "1110552476");
//
//        Session mailSession = Session.getDefaultInstance(props, null);
//        mailSession.setDebug(true);
//        Transport transport = mailSession.getTransport();
//
//        MimeMessage message = new MimeMessage(mailSession);
//        message.setSubject("HTML  mail with images");
//        message.setFrom(new InternetAddress("jjvanegas67@misena.edu.co"));
//        message.addRecipient(Message.RecipientType.TO,
//             new InternetAddress("jjvanegas67@misena.edu.co"));
//
//        //
//        // This HTML mail have to 2 part, the BODY and the embedded image
//        //
//        MimeMultipart multipart = new MimeMultipart("related");
//
//        // first part  (the html)
//        BodyPart messageBodyPart = new MimeBodyPart();
//        String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
//        messageBodyPart.setContent(htmlText, "text/html");
//
//        // add it
//        multipart.addBodyPart(messageBodyPart);
//        
//        // second part (the image)
//        messageBodyPart = new MimeBodyPart();
//        DataSource fds = new FileDataSource
//          ("C:\\logoETOC.png");
//        messageBodyPart.setDataHandler(new DataHandler(fds));
//        messageBodyPart.setHeader("Content-ID","<image>");
//
//        // add it
//        multipart.addBodyPart(messageBodyPart);
//
//        // put everything together
//        message.setContent(multipart);
//
//        transport.connect();
//        transport.sendMessage(message,
//            message.getRecipients(Message.RecipientType.TO));
//        transport.close();
        
        
        //VALIDAR CONEXIÓN... :D
//        if(UtilOne.validarConexion()){
//            System.out.println("Conectado.");
//        }else{
//            System.out.println("Desconectado.");
//        }
        
        ReunionManaged rM = new ReunionManaged();
                
        try {
            rM.enviarCorreos("jjvanegas67@misena.edu.co");
            System.out.println("Correo enviado.");
        } catch (Exception e) {
            System.out.println("No se pudo enviar el correo."+e.getMessage());
        }
        }
}