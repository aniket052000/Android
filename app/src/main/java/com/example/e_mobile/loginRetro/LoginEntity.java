package com.example.e_mobile.loginRetro;

import java.io.Serializable;

public class LoginEntity implements Serializable {
    private String email;
    private String password;

    public LoginEntity(String email, String password) {

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
