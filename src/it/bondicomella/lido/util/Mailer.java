package it.bondicomella.lido.util;

import it.bondicomella.lido.biglietteria.model.Prenotazione;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailer {

    protected Session oSession;

    public Mailer(){
        String hostSmtpUser = "gginuzzo@gmail.com";
        String host = "smtp.gmail.com";
        String hostPort = "587";
        String hostSmtpPassword = "Prototunlea55";


        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.user", hostSmtpUser);
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", hostPort);
        properties.setProperty("mail.smtp.auth", "true");

        this.oSession = Session.getInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(hostSmtpUser, hostSmtpPassword);
            }
        });
    }

    public void sendMailNewPrenotazione(Prenotazione prenotazione, String emailUtente){
        try {

            MimeMessage message = new MimeMessage(this.oSession);
            message.setFrom(new InternetAddress("no-reply@lidoBoCo.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailUtente));
            message.setSubject("Lido - Nuova Prenotazione");
            message.setContent("Salve, ecco il codice della prenotazione del giorno: "
                    + prenotazione.getDataPrenotazione() + " valida dalle ore: "
                    + prenotazione.getOraInizio() + " alle ore: "
                    + prenotazione.getOraFine() + ". <br><h1>" + prenotazione.getCodicePrenotazione() + "</h1>", "text/html");

            // Send message
            Transport.send(message);

        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }

    public void sendMailAnnullaPrenotazione(Prenotazione prenotazione, String emailUtente){
        try {

            MimeMessage message = new MimeMessage(this.oSession);
            message.setFrom(new InternetAddress("no-reply@lidoBoCo.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailUtente));
            message.setSubject("Lido - Avviso annullamento prenotazione");
            message.setContent("Salve, ci dispiace per l'incoveniente ma la sua prenotazione riferita alla data: "
                    + prenotazione.getDataPrenotazione() + " valida dalle ore: "
                    + prenotazione.getOraInizio() + " alle ore: "
                    + prenotazione.getOraFine() + " è stata <b>cancellata</b>.", "text/html");

            // Send message
            Transport.send(message);

        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }
}
