package com.danit.models;

import lombok.Data;

@Data
public class User {
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final String photo;
}
