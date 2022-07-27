package com.ECommerence.entity;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import javax.mail.*;

import javax.mail.internet.*;


public class Send_Mail extends  Mail_Config {

    public String mail_receive = "" ;
    private HttpSession session  ;
    private int Verify_number = 0 ;
   private Properties props =  new Properties() ;

    public Send_Mail(HttpServletRequest request) {

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host" ,  Mail_Config.Host_Name);

        props.put("mail.smtp.socketFactory.port", Mail_Config.SSL_PORT);

        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", Mail_Config.TSL_PORT);

    }

    public String getMail_Send() {
        return mail_receive;
    }

    public void setMail_Send(String mail_Send) {
        this.mail_receive = mail_Send;
    }

    public void Send_Email(String message) {
        System.out.println("Mail  recieve + " + this.mail_receive);


        javax.mail.Session session =  javax.mail.Session.getDefaultInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println();
                return new PasswordAuthentication(Mail_Config.APP_EMAIL, Mail_Config.APP_PASSWORD);
            }
        });

        try {
            System.out.println(mail_receive);

            MimeMessage mimeMessage =  new MimeMessage(session) ;

            mimeMessage.setRecipients(Message.RecipientType.TO  , InternetAddress.parse(mail_receive));

            mimeMessage.setSubject("Your single-use code");

            mimeMessage.setText(String.valueOf(message));

            Transport.send(mimeMessage);

        }
        catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(e) ;
        }
        return ;
    }

    public String  create_verify_number( HttpServletRequest request ) {

        HttpSession Session =  request.getSession() ;

        int min  = 1000 ;
        int max  = 9999 ;
        this.Verify_number = (int)Math.floor(Math.random()*(max-min+1)+min);

        return  String.valueOf(this.Verify_number) ;

    }

//    public static void main(String[] args) {
//        Send_Mail send_mail =  new Send_Mail()     ;
//        send_mail.Send_Email();
//    }
}
