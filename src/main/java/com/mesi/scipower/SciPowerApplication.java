package com.mesi.scipower;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SciPowerApplication {

    public static void main(String[] args) {
        Application.launch(SciPowerGUI.class, args);
    }

}
