package com.mesi.scipower.controller;

import com.mesi.scipower.service.ParserService;
import com.mesi.scipower.service.SwitchControllerService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class HelloController {

    private final SwitchControllerService controllerService;
    private final ApplicationContext applicationContext;
    private final ParserService parserService;
    private final FileChooser fileChooser;

    @FunctionalInterface
    private interface BackgroundTask {
        void process();
    }

    @Autowired
    public HelloController(@Qualifier("CSV") ParserService parserService,
                           SwitchControllerService controllerService,
                           ApplicationContext applicationContext) {
        this.controllerService = controllerService;
        this.applicationContext = applicationContext;
        this.parserService = parserService;

        fileChooser = new FileChooser();

        log.info("Application Context ID: " + this.applicationContext.getId());
        log.info("hello-view is loaded");
    }

    @FXML
    private TextField loginField;

    @FXML
    protected void initialize() {
        log.info("hello-view is initialized");
    }

    @FXML
    protected void chooseFiles(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        List<File> selectedFile = fileChooser.showOpenMultipleDialog(stage);
        File selectedFile = fileChooser.showOpenDialog(stage);
        log.info(selectedFile.toString());

        parserService.parseFile(selectedFile.getAbsolutePath());

        loginField.getScene().getWindow().hide();
        controllerService.switchController(
                "load-data", applicationContext
        );
    }

    @FXML
    protected void getCredentials() throws IOException {
        Thread csvParserThread = getThread(
                () -> parserService.parseFile("ar2001"),
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
                "load-data", applicationContext
        );
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
