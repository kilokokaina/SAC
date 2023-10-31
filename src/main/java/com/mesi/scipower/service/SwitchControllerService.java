package com.mesi.scipower.service;

import org.springframework.context.ApplicationContext;

import java.io.IOException;

public interface SwitchControllerService {

    void switchController(String FXMLPath, ApplicationContext context) throws IOException;

}
