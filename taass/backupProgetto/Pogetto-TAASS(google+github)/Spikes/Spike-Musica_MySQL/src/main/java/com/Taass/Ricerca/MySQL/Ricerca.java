package com.Taass.Ricerca.MySQL;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "Ricerca")
public class Ricerca {

    @Id
    @SequenceGenerator(
            name = "ricerca_id_sequence",
            sequenceName= "ricerca_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ricerca_id_sequence"
    )
    @Column (nullable = false)
    private Integer IdRi;
    private String Titoloricerca;

    public Ricerca (Integer idri, String titoloricerca){
        this.IdRi = idri;
        Titoloricerca = titoloricerca;
    }

    public Ricerca(){
    }

    public Integer getIdRi() {
        return IdRi;
    }

    public void setIdRi(Integer idRi) {
        IdRi = idRi;
    }

    public String getTitoloricerca() {
        return Titoloricerca;
    }

    public void setTitoloricerca(String titoloricerca) {
        Titoloricerca = titoloricerca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ricerca ricerca = (Ricerca) o;
        return Objects.equals(IdRi, ricerca.IdRi) && Objects.equals(Titoloricerca, ricerca.Titoloricerca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdRi, Titoloricerca);
    }

    @Override
    public String toString() {
        return "Ricerca{" +
                ", IdSh=" + IdRi +
                ", Titoloricerca=" + Titoloricerca + '\'' +
                '}';
    }
}
