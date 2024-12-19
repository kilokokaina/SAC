package org.work.scipower.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.work.scipower.model.ProjectModel;
import org.work.scipower.service.impl.ProjectServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RestController
@RequestMapping("api/project")
public class ProjectAPI {

    private final JFileChooser fileChooser;
    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectAPI(ApplicationContext context, ProjectServiceImpl projectService) {
        fileChooser = (JFileChooser) context.getBean("fileChooser");
        this.projectService = projectService;
    }

    @GetMapping("open_dialog")
    public @ResponseBody String openDirectoryChooser() throws InterruptedException, InvocationTargetException {
        AtomicReference<String> dirPath = new AtomicReference<>("");

        EventQueue.invokeAndWait(() -> {
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                dirPath.set(fileChooser.getSelectedFile().getAbsolutePath());
                log.info(dirPath.get());
            }
        });

        return dirPath.get();
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<ProjectModel>> getProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

}
