package com.mesi.scipower;

import com.mesi.scipower.config.StageReadyEvent;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
public class SciPowerGUI extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
            applicationContext.registerBean(Application.class, () -> SciPowerGUI.this);
            applicationContext.registerBean(Parameters.class, this::getParameters);
            applicationContext.registerBean(HostServices.class, this::getHostServices);
        };
        this.context = new SpringApplicationBuilder()
                .sources(SciPowerApplication.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) {
        this.context.publishEvent(new StageReadyEvent(stage));
        log.info("Application Context ID: " + this.context.getId());
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }
}
