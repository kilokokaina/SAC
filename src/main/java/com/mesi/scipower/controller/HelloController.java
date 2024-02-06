package com.mesi.scipower.controller;

import com.mesi.scipower.service.ParserService;
import com.mesi.scipower.service.SwitchControllerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class HelloController {

    private final SwitchControllerService controllerService;
    private final ApplicationContext applicationContext;
    private final ParserService parserService;
    private final FileChooser fileChooser;

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
    protected void initialize() {
        log.info("hello-view is initialized");
    }

    @FXML
    protected void chooseFiles(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        controllerService.switchController(
                "load-data", applicationContext
        );
    }

}
