package com.mesi.scipower.controller;

import com.mesi.scipower.pojo.User;
import com.mesi.scipower.service.impl.CSVParserService;
import com.mesi.scipower.service.impl.SwitchControllerServiceImpl;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class HelloController {

    private final SwitchControllerServiceImpl controllerService;
    private final ApplicationContext applicationContext;
    private final CSVParserService csvParserService;
    private final User globalUser;

    @Autowired
    public HelloController(SwitchControllerServiceImpl controllerService,
                           ApplicationContext applicationContext,
                           CSVParserService csvParserService) {
        this.controllerService = controllerService;
        this.applicationContext = applicationContext;
        this.csvParserService = csvParserService;

        globalUser = (User) applicationContext.getBean("sessionUser");

        log.info("Application Context ID: " + this.applicationContext.getId());
        log.info("hello-view is loaded");
    }

    @FXML
    private Label logLabel;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected  void initialize() {
        log.info("hello-view is initialized");
    }

    @FXML
    protected void getCredentials() throws IOException {
        if (loginField.getText().equals(globalUser.getLogin()) &&
                passwordField.getText().equals(globalUser.getPassword())) {

            Thread csvParserThread = getThread();
            csvParserThread.start();

            loginField.getScene().getWindow().hide();
            controllerService.switchController(
                    "add-item", applicationContext
            );

        } else {
            logLabel.setText("Wrong credentials");
        }
    }

    private Thread getThread() {
        Task<Void> backgroundParse = new Task<>() {
            @Override
            protected Void call() {
                csvParserService.parseFile("csv-1");

                return null;
            }
        };

        backgroundParse.setOnSucceeded(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Parsing process completed");
            alert.show();
        });

        Thread csvParserThread = new Thread(backgroundParse);

        csvParserThread.setDaemon(true);
        return csvParserThread;
    }

}
