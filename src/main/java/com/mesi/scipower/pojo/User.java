package com.mesi.scipower.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String login;
    private String password;

    @Override
    public String toString() {
        return String.format("Login: %s\nPassword: %s",
                getLogin(), getPassword()
        );
    }

}
