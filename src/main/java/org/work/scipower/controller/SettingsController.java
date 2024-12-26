package org.work.scipower.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.work.scipower.service.impl.ProjectServiceImpl;

@Slf4j
@Controller
public class SettingsController {

    private final ProjectServiceImpl projectService;
    private final ApplicationContext context;

    @Autowired
    public SettingsController(ApplicationContext context, ProjectServiceImpl projectService) {
        this.projectService = projectService;
        this.context = context;
    }

    @GetMapping("settings")
    public String settings(Model model) {
        model.addAttribute("projects", projectService.findAll());
        log.info("Project: {}", context.getBean("currentProject"));

        return "settings";
    }

}
