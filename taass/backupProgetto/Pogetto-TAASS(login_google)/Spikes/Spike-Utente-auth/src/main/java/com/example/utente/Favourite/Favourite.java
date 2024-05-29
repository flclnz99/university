package com.example.utente.Favourite;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "Favourite")
public class Favourite {

    @Id
    @SequenceGenerator(
            name = "Favourite_id_sequence",
            sequenceName = "Favourite_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Favourite_id_sequence"
    )
    @Column(nullable = false)
    private Long id;
    private String type;
    private Long idFav;


    public Favourite() {
    }

    public void setId(Long id) {this.id = id;}

    public void setType(String type) {this.type = type;}

    public void setIdFav(Long idFav) {this.idFav = idFav;}

    @Override
    public String toString() {
        return "Favourite{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", idFav=" + idFav +
                '}';
    }
}




