package com.example.SimpleWebApp.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class User {

    private UUID id;

    private String fullName;

    private String email;

    private String dob;

    private Integer age;
}
