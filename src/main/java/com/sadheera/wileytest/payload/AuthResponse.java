package com.sadheera.wileytest.payload;

public class AuthResponse {
    private boolean success;
    private String message;
    private String accessToken;
    private String tokenType;

    public AuthResponse(String accessToken, String message) {
        this.success = true;
        this.message = message;
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
