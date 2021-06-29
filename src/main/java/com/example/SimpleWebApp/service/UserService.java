package com.example.SimpleWebApp.service;

import com.example.SimpleWebApp.model.User;
import com.example.SimpleWebApp.repository.UserProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Service
public class UserService {

    @Autowired
    private UserProxy userProxy;

    public User getUser(final UUID id) {
        return userProxy.getUser(id);
    }

    public User[] getUsers() {
        return userProxy.getUsers();
    }

    public void deleteUser(final UUID id) {
        userProxy.deleteUser(id);
    }

    public User saveUser(User user) {
        User savedUser;
        System.out.println("id of new user " + user.getId());
        if (user.getId() == null) {
            savedUser = userProxy.createUser(user);
        } else {
            savedUser = userProxy.updateUser(user);
        }

        return savedUser;
    }
}
