package mx.edu.utez.sigev.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailService {
    final private String de = "int.sigev@gmail.com";
    final private String pass = "djnzmigbndkbdoqxccxslportyv";

    public EmailService() {
    }

    public void sendEmail(String para, String asunto, String mensaje) {

        // Agregar la propiedad para habilitar la depuración


        Properties props = new Properties();
        props.put("mail.debug", "true"); //debug mode
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.transport.protocol", "smtp");

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(de, pass);
            }
        };

        Session session = Session.getInstance(props, auth);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(de));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(asunto);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mensaje, "text/html");

            // MimeBodyPart adjunto = new MimeBodyPart();
            //adjunto.attachFile(archivo);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
           // multipart.addBodyPart(adjunto);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Mensaje enviado!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
