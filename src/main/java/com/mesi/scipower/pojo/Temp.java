package com.mesi.scipower.pojo;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Temp {
    private String tempString;

    @PostConstruct
    public void init() {
        this.tempString = "Something";
    }

}
