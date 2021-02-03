package upp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import upp.backend.model.ConfirmationToken;
import upp.backend.model.User;
import upp.backend.repository.ConfirmationTokenRepository;

import java.util.UUID;

@Service("emailSenderService")
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public void EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public void send(String subject, User user, String text){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByUser(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("literalnoudruzenje26@gmail.com");

        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8081/confirm-account?token="+confirmationToken.getConfirmationToken());

        sendEmail(mailMessage);
    }
    
    
    public void sendToWriter(String subject, User user, String text){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByUser(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Notification request to upload book");
        mailMessage.setFrom("literalnoudruzenje26@gmail.com");

        mailMessage.setText("Please upload the full text ");

        sendEmail(mailMessage);
    }
    
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return  uuid;
    }
    
    public void sendEmail(String subject, User user, String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setFrom("literalnoudruzenje26@gmail.com");
        mailMessage.setText(text);

        sendEmail(mailMessage);
    }
}
