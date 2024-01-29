package com.mesi.scipower;

import com.mesi.scipower.model.ParseDocument;
import com.mesi.scipower.pojo.Temp;
import com.mesi.scipower.pojo.User;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SciPowerApplication {

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

    @Lazy
    @Bean(name = "parsedDocuments")
    public List<ParseDocument> parseDocumentList() {
        return new ArrayList<>();
    }

}
