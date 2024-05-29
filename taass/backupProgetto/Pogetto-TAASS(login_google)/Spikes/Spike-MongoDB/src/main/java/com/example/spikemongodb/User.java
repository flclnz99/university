package com.example.spikemongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


// Questa classe viene iniettata come Documenti BSON di MongoDB
// La collection User viene creata automaticamente con l'annotazione @Document

@Document
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true) // Non possono esistere 2 documenti con la stessa email
    private String email;
    private String password;
    private List<String> favouriteSongs;


    public User(String id, String firstName, String lastName, String email, String password, List<String> favouriteSongs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.favouriteSongs = favouriteSongs;
    }

    public User(String firstName, String lastName, String email, String password, List<String> favouriteSongs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.favouriteSongs = favouriteSongs;
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFavouriteSongs() {
        return favouriteSongs;
    }

    public void setFavouriteSongs(List<String> favouriteSongs) {
        this.favouriteSongs = favouriteSongs;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", favouriteSongs=" + favouriteSongs +
                '}';
    }
}
