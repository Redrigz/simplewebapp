package com.example.SimpleWebApp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.example.webapp")
public class CustomProprities {

    private String apiUrl;
}
