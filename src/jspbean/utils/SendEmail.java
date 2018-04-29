package jspbean.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * SendEmail.java
 */
public class SendEmail {

  public void sendMail() throws MessagingException {
    final String smtpServer = "smtp.gmail.com";
    final String userAccount = ""; // Sender Account.
    final String password = ""; // Password/Application Specific Password.
    final String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
    final String smtpPort = "587";
    final String PORT = "465";

    final Properties props = new Properties();
    props.put("mail.smpt.host", smtpServer);
    props.put("mail.smtp.user", userAccount);
    props.put("mail.smtp.password", password);
    props.put("mail.smtp.port", smtpPort);
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.debug", "false");
    props.put("mail.smtp.socketFactory.port", PORT);
    props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
    props.put("mail.smtp.socketFactory.fallback", "false");

    Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userAccount, password);
          }
        });
    MimeMessage mimeMessage = new MimeMessage(session);
    final Address toAddress = new InternetAddress("bt080603@bigmir.net"); // toAddress
    final Address fromAddress = new InternetAddress(userAccount);
    mimeMessage.setContent("This is a test mail...", "text/html; charset=UTF-8");
    mimeMessage.setFrom(fromAddress);
    mimeMessage.setRecipient(javax.mail.Message.RecipientType.TO, toAddress);
    mimeMessage.setSubject("Test Mail 2...");
    Transport transport = session.getTransport("smtp");
    transport.connect(smtpServer, userAccount, password);
    transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
  }

  public static void main(String[] args){
    SendEmail se = new SendEmail();
    try {
      se.sendMail();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}

