package com.mesi.scipower;

import com.mesi.scipower.model.Author;
import com.mesi.scipower.model.Document;
import com.mesi.scipower.pojo.Temp;
import com.mesi.scipower.pojo.User;
import com.mesi.scipower.service.AuthorService;
import com.mesi.scipower.service.DocumentService;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

@Slf4j
@SpringBootApplication
public class SciPowerApplication {

    private final DocumentService documentService;
    private final AuthorService authorService;

    @Autowired
    public SciPowerApplication(DocumentService documentService,
                               AuthorService authorService) {
        this.documentService = documentService;
        this.authorService = authorService;
    }

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
            Document document1 = documentService.findByTitle("document-1");
            Document document2 = documentService.findByTitle("document-2");
            Document document3 = documentService.findByTitle("document-3");

            Set<Author> authors1 = document1.getAuthors();
            Set<Author> authors2 = document2.getAuthors();
            Set<Author> authors3 = document3.getAuthors();

            log.info(authors1.toString());
            log.info(authors2.toString());
            log.info(authors3.toString());
        };
    }

}
