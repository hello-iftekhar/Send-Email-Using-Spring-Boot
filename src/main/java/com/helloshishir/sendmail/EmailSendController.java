package com.helloshishir.sendmail;

import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSendController {
    private final EmailService emailService;

    public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Email email) {
        return emailService.sendEmail(email);
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@ModelAttribute Email email) throws MessagingException {
        return emailService.sendMailWithAttachment(email);
    }
}
