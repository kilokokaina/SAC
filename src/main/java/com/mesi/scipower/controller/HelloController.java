package com.mesi.scipower.controller;

import com.mesi.scipower.service.impl.SwitchControllerServiceImpl;
import javafx.fxml.FXML;
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

    @Autowired
    public HelloController(SwitchControllerServiceImpl controllerService,
                           ApplicationContext applicationContext) {
        this.controllerService = controllerService;
        this.applicationContext = applicationContext;

        log.info("Application Context ID: " + this.applicationContext.getId());
        log.info("hello-view is loaded");
    }

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
//        log.info(new User(loginField.getText(), passwordField.getText()).toString());

//        loginField.getScene().getWindow().hide();
        controllerService.switchController(
                "add-item", applicationContext
        );
    }

}
