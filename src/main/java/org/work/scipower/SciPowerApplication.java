package org.work.scipower;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.annotation.ApplicationScope;
import org.work.scipower.model.ParserDocument;
import org.work.scipower.model.ProjectModel;
import org.work.scipower.model.Reference;
import org.work.scipower.model.graph.Edge;
import org.work.scipower.model.graph.Node;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@EnableAsync
@SpringBootApplication
public class SciPowerApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(SciPowerApplication.class);
        builder.headless(false).run(args);
    }

    @Bean
    @ApplicationScope
    public CopyOnWriteArrayList<ParserDocument> dataList() {
        return new CopyOnWriteArrayList<>();
    }

    @Bean
    @ApplicationScope
    public Set<Edge> edgeList() {
        return ConcurrentHashMap.newKeySet();
    }

    @Bean
    @ApplicationScope
    public Set<Node> nodeList() {
        return ConcurrentHashMap.newKeySet();
    }

    @Bean
    @ApplicationScope
    public Set<Reference> referenceList() {
        return ConcurrentHashMap.newKeySet();
    }

    @Bean
    @ApplicationScope
    public ProjectModel currentProject() {
        return new ProjectModel();
    }

    @Bean
    @ApplicationScope
    public JFileChooser fileChooser() {
        var dirChooser = new JFileChooser();

        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dirChooser.setDialogType(JFileChooser.SAVE_DIALOG);

        return dirChooser;
    }

    @Bean
    @ApplicationScope
    public List<String> HEADERS() {
        var result = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/model"))) {
            String line;
            while((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        var executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("parser-");
        executor.initialize();

        return executor;
    }

}
