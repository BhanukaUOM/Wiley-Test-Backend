package com.sadheera.wileytest.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ResetPasswordVerifyRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private Integer token;

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

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }
}
