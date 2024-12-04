package com.example.demo.dummy;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Dummy {

    @Id
    @GeneratedValue
    private Long id;
    private String email;


    public Dummy(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Dummy(String email) {
        this.email = email;
    }

    public Dummy() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
