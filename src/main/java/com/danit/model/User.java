package com.danit.model;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String photo;
    private String profession;

    public User(int id, String name, String surname, String email, String photo, String profession) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.photo = photo;
        this.profession = profession;
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

    public String getProfession() {return profession;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
