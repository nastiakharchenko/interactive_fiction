package org.module_three.entity;


import lombok.Getter;
import org.module_three.service.JsonService;

import java.io.IOException;
import java.util.List;

import static org.module_three.constant.FileNameConstants.FILE_USERS;

@Getter
public class UserCollection {
    private List<User> users;

    public UserCollection() {
        try{
            users = JsonService.readFileJsonUser(FILE_USERS);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Boolean searchUser(User user){
        return users.stream()
                .anyMatch(currentUser -> currentUser.getUsername().equals(user.getUsername())
                    && currentUser.getPassword().equals(user.getPassword()));
    }

    public Boolean searchUsername(String username){
        return users.stream()
                .anyMatch(currentUser -> currentUser.getUsername().equals(username));
    }
}
