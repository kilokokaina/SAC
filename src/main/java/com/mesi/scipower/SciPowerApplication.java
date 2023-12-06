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

import java.util.ArrayList;
import java.util.List;

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

            List<Author> authors = authorService.findAll();

            List<Author> authors1 = new ArrayList<>();
            List<Author> authors2 = new ArrayList<>();
            List<Author> authors3 = List.of(authors.get(4));

            for (int i = 0; i < authors.size(); i++) {
                if (authors.get(i).getId() < 3) authors1.add(authors.get(i));
                else authors2.add(authors.get(i));
            }

            document1.setAuthors(authors1);
            document2.setAuthors(authors2);
            document3.setAuthors(authors3);

            documentService.save(document1);
            documentService.save(document2);
            documentService.save(document3);
        };
    }

}
