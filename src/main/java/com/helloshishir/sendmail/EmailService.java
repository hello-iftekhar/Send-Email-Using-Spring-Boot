package com.helloshishir.sendmail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendEmail(Email email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Iftekhar Hossain<"+sender+">");
            mailMessage.setTo(email.getRecipient());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getMessage());

            javaMailSender.send(mailMessage);
            return "Email sent successfully!";
        } catch (Exception e) {
            return "Email sending error!";
        }
    }

    public String sendMailWithAttachment(Email email) throws MessagingException {
       try {
           // Create mime message
           MimeMessage mimeMessage = javaMailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper
                   = new MimeMessageHelper(mimeMessage, true);

           mimeMessageHelper.setFrom("Iftekhar Hossain<"+sender+">");
           mimeMessageHelper.setTo(email.getRecipient());
           mimeMessageHelper.setSubject(email.getSubject());
           mimeMessageHelper.setText(email.getMessage());

           // adding the attachment
           mimeMessageHelper.addAttachment(email.getAttachment().getOriginalFilename(),
                   email.getAttachment());

           // send the mail
           javaMailSender.send(mimeMessage);
           return "Mail sent successfully!";
       } catch (Exception e) {
           return "Mail sending error!";
       }
    }
}
