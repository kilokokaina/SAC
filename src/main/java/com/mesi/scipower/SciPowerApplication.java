package com.mesi.scipower;

import com.mesi.scipower.model.Document;
import com.mesi.scipower.pojo.Temp;
import com.mesi.scipower.pojo.User;
import com.mesi.scipower.repository.*;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@Slf4j
@SpringBootApplication
public class SciPowerApplication {

    @Autowired
    private DocumentRepository documentRepository;

    public static void main(String[] args) {
        Application.launch(SciPowerGUI.class, args);
    }

    @Bean(name = "sessionUser")
    @Lazy
    public User user(Temp temp) {
        User sessionUser = new User("KidJesus", "Rand0m-password");
        sessionUser.setTemp(temp);
        log.info("Session user was created: " + sessionUser);

        return sessionUser;
    }

    @Bean
    public CommandLineRunner cmd() {
        return args -> {
            Document document1 = documentRepository.findByTitle("document-1");
            Document document2 = documentRepository.findByTitle("document-2");
            Document document3 = documentRepository.findByTitle("document-3");

            log.info(document1.toString());
            log.info(document2.toString());
            log.info(document3.toString());
        };
    }

}
