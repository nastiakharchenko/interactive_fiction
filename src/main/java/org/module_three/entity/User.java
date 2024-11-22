package org.module_three.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class User {
    private final String username;
    private final String password;

    @JsonCreator
    public User(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        this.username = username;
        this.password = password;
    }
}
