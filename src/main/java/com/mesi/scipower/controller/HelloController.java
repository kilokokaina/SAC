package com.mesi.scipower.controller;

import com.mesi.scipower.pojo.User;
import com.mesi.scipower.service.impl.CSVParserService;
import com.mesi.scipower.service.impl.SwitchControllerServiceImpl;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
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

    @FunctionalInterface
    private interface BackgroundTask {
        void process();
    }

    @Autowired
    public HelloController(SwitchControllerServiceImpl controllerService,
                           ApplicationContext applicationContext,
                           CSVParserService csvParserService) {
        this.controllerService = controllerService;
        this.applicationContext = applicationContext;
        this.csvParserService = csvParserService;

        this.globalUser = (User) applicationContext.getBean("sessionUser");

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
    protected void initialize() {
        log.info("hello-view is initialized");
    }

    @FXML
    protected void getCredentials() throws IOException {
        if (loginField.getText().equals(globalUser.getLogin()) &&
                passwordField.getText().equals(globalUser.getPassword())) {
            Thread csvParserThread = getThread(
                    () -> csvParserService.parseFile("csv-1"),
                    startEvent -> {},
                    endEvent -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Parsing");
                        alert.setContentText("Parsing process completed");
                        alert.showAndWait();
                    }
            );
            csvParserThread.start();

            loginField.getScene().getWindow().hide();
            controllerService.switchController(
                    "add-item", applicationContext
            );
        } else {
            Thread wrondCredentialsThread = getThread(
                    () -> {
                        try {
                            Thread.sleep(5_000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }, startEvent -> logLabel.setText("Wrong credentials"),
                    endEvent -> logLabel.setText("")
            );
            wrondCredentialsThread.start();
        }
    }

    private Thread getThread(BackgroundTask backgroundTask,
                             EventHandler<WorkerStateEvent> startEvent,
                             EventHandler<WorkerStateEvent> endEvent) {
        Task<Void> background = new Task<>() {
            @Override
            protected Void call() throws Exception {
                backgroundTask.process();
                return null;
            }
        };
        background.setOnRunning(startEvent);
        background.setOnSucceeded(endEvent);

        Thread tread = new Thread(background);
        tread.setDaemon(true);

        return tread;
    }

}
