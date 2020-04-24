package com.example.noname;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailJob extends AsyncTask<MailJob.Mail,Void,Void> {

    private final String user;
    private final String password;

    public MailJob(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

    @Override
    protected Void doInBackground(Mail... mail) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(user,password);
            }
                });
        for(Mail objmail:mail){
            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(objmail.from));
                message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(objmail.to));
                message.setSubject(objmail.subject);
                message.setText(objmail.content);

                Transport.send(message);

            }catch (MessagingException e){
                Log.d("MailJob", e.getMessage());
            }
        }
        return null;
    }

    //clase Mail
    public static class Mail{
        private final String subject;
        private final String content;
        private final String from;
        private final String to;

        public Mail(String subject, String content, String from, String to) {
            this.subject = subject;
            this.content = content;
            this.from = from;
            this.to = to;
        }
    }
}
