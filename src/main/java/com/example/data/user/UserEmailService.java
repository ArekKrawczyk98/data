package com.example.data.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@AllArgsConstructor
@Getter
public class UserEmailService {

    private final JavaMailSenderImpl mailSender;
    private final UserEmailConfig emailConfig;


    public static UserEmailService createService() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        UserEmailConfig emailConfig = new UserEmailConfig();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        return new UserEmailService(mailSender,emailConfig);
    }

    public void sendEmail(User user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("coronaVirusDataAnalyze@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Test");
        message.setText("some message to test");
        mailSender.send(message);
    }



}
