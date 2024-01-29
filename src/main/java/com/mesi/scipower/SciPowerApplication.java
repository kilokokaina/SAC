package com.mesi.scipower;

import com.mesi.scipower.model.ParseDocument;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@EnableAsync
@SpringBootApplication
public class SciPowerApplication {

    public static void main(String[] args) {
        Application.launch(SciPowerGUI.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("parser-");
        executor.initialize();

        return executor;
    }

    @Lazy
    @Bean(name = "parsedDocuments")
    public List<ParseDocument> parseDocumentList() {
        return new ArrayList<>();
    }

}
