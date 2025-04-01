package com.ecommerce.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

@ApplicationScoped
public class EmailService {

    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        final String username = "4575190a0ef3cf"; // Copiado de tu captura
        final String password = "f492e3a9155f66"; // Pegá el completo

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ecommerce@localhost"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);
            System.out.println("✅ Correo enviado (capturado por Mailtrap)");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("❌ Error al enviar correo: " + e.getMessage());
        }
    }
}

