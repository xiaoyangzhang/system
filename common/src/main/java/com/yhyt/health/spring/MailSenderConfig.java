package com.yhyt.health.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailSenderConfig {

//    @Value("${spring.mail.properties}")
    private Properties javaMailProperties = new Properties();
//    @Value("${spring.mail.properties.mail.smtp.auth}")
//    private boolean auth;
//    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
//    private boolean enable;
//    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
//    private boolean required;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    @Bean
    @Primary
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
//        javaMailProperties.setProperty("")
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }

}
