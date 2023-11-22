package com.mesi.scipower.pojo;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
//@Component
public class User {

    private String login;
    private String password;

    private Temp temp;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Login: %s\nPassword: %s",
                getLogin(), getPassword()
        );
    }

}
