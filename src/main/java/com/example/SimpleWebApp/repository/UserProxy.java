package com.example.SimpleWebApp.repository;

import com.example.SimpleWebApp.config.CustomProprities;
import com.example.SimpleWebApp.model.ResponseResult;
import com.example.SimpleWebApp.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Component
public class UserProxy {

    @Autowired
    private CustomProprities props;
    private final String baseApiUrl;
    private final String userUrl;

    public UserProxy(CustomProprities props) {
        baseApiUrl = props.getApiUrl();
        userUrl = baseApiUrl + "/user";
    }

    public User[] getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseResult<User[]>> response = restTemplate.exchange(
                userUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseResult<User[]>>() {}
        );

        log.debug("get Users " + response.getStatusCode().toString());
        return response.getBody().getData();
    }

    public User getUser(UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseResult<User>> response = restTemplate.exchange(
                userUrl + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseResult<User>>() {}
        );

        log.debug("Get User " + response.getStatusCode().toString());
        return response.getBody().getData();
    }

    public User createUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<User>(user);
        ResponseEntity<User> response = restTemplate.exchange(
                userUrl,
                HttpMethod.POST,
                request,
                User.class
        );

        log.debug("Create User " + response.getStatusCode().toString());
        return response.getBody();
    }

    public User updateUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<User>(user);
        ResponseEntity<ResponseResult<User>> response = restTemplate.exchange(
                userUrl + '/' + user.getId(),
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<ResponseResult<User>>() {}
        );

        log.debug("Update User " + response.getStatusCode().toString());
        return response.getBody().getData();
    }

    public void deleteUser(UUID id) {
        System.out.println("================ " + id + "==================");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                userUrl + "/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        log.debug("Delete User " + response.getStatusCode().toString());
    }
}
