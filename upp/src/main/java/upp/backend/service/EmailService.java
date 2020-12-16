package upp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import upp.backend.model.User;

import java.util.UUID;

@Service("emailSenderService")
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public void EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public void send(String subject, User user, String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("literalnoudruzenje26@gmail.com");

        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8081/confirm-account?token="+user.getEmail());

        sendEmail(mailMessage);
    }
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return  uuid;
    }
}
