package com.mesi.scipower.service.impl;

import com.mesi.scipower.service.SwitchControllerService;
import jakarta.annotation.PostConstruct;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class SwitchControllerServiceImpl implements SwitchControllerService {

    private static final String FXML_URL = "com.mesi.scipower/%s.fxml";

    @Value("${spring.application.ui.title}")
    private String applicationTitle;

    @PostConstruct
    public void init() {
        log.info(this.getClass().getSimpleName() + " was created");
    }

    @Override
    public void switchController(String FXMLPath, ApplicationContext context) throws IOException {
        Resource fxml = new ClassPathResource(String.format(FXML_URL, FXMLPath));

        FXMLLoader fxmlLoader = new FXMLLoader(fxml.getURL());
        fxmlLoader.setControllerFactory(context::getBean);
        fxmlLoader.load();

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(applicationTitle);
        stage.show();
    }
}
