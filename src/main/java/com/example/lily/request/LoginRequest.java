package com.example.lily.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    private String userName;
    private String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}