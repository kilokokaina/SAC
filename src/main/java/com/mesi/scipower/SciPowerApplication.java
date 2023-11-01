package com.mesi.scipower;

import com.mesi.scipower.pojo.User;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Slf4j
@Configuration
@SpringBootApplication
public class SciPowerApplication {

    public static void main(String[] args) {
        Application.launch(SciPowerGUI.class, args);
    }

    @Bean
    @Scope("singleton")
    public User sessionUser() {
        User sessionUser = new User("KidJesus", "Rand0m-password");
        log.info("Session user created: " + sessionUser);

        return sessionUser;
    }

}
