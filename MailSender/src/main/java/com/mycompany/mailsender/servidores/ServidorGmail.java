/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mailsender.servidores;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class ServidorGmail implements ServidorCorreo{
    
    private String mailTO, subject, content;
    
    private Properties properties = new Properties();
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    public boolean autentificacion(String email, String password) {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", email, password);
            transport.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void enviarCorreo(Authenticator auth, String emailFrom, String mailTO, String subject, String content) {
        this.mailTO = mailTO;
        this.subject = subject;
        this.content = content;

        Session mSession = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(mSession);

        try {
            msg.setFrom(new InternetAddress(emailFrom));
            InternetAddress[] toAdress = {
                new InternetAddress(mailTO)
            };
            msg.setRecipients(Message.RecipientType.TO, toAdress);
            msg.setSubject(subject);
            msg.setSentDate(new java.util.Date());

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(content, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            msg.setContent(multipart);

            Transport.send(msg);
            JOptionPane.showMessageDialog(null, "Correo enviado con exito!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al enviar correo!! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


