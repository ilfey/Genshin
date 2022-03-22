package com.example.data.remotely.auth;

public class LoginBody {
    public String email;
    public String password;

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
