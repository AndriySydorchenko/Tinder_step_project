package com.danit.model;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String photo;

    public User(int id, String name, String surname, String email, String photo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
