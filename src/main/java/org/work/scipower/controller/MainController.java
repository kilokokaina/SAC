package org.work.scipower.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.work.scipower.model.ParseDocument;

import java.util.List;

@Slf4j
@Controller
public class MainController {

    private final ApplicationContext context;

    @Autowired
    public MainController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping
    @SuppressWarnings("unchecked")
    public String home(HttpSession session) {
        log.info("Rows: {}", ((List<ParseDocument>) context.getBean("dataList")).size());
        log.info("JSESSIONID={}", session.getId());
        return "home";
    }

}