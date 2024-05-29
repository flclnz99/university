package com.Taass.Ricerca.MySQL.album;

import com.Taass.Ricerca.MySQL.artists.Artist;
import com.Taass.Ricerca.MySQL.songs.Song;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Album")
public class Album {
    @Id
    @SequenceGenerator(
            name = "Album_id_sequence",
            sequenceName = "Album_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Album_id_sequence"
    )
    @Column(nullable = false)
    private Long id;
    private String title;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="artists_album")
    private Artist artist;
    @OneToMany(mappedBy="album")
    private Set<Song> songs = new HashSet<>();
    private int year;
    private int nsong;
    private String hp; //Casa discografica

    public Album(){}

    public Long getIdAlbum() {return this.id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return this.title;}

    public void setTitle(String title) {this.title = title;}

    public Artist getArtist() {return this.artist;}

    public Set<Song> getSongs() {return songs;}

    public int getYear() {return this.year;}

    public void setYear(int year) {this.year = year;}

    public int getNSong() {return this.nsong;}

    public void setNSong(int nSong) {this.nsong = nSong;}

    public String getHp() {return this.hp;}

    public void setHp(String hp) {this.hp = hp;}

    public void assignArtist(Artist artist){this.artist=artist;}

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", year=" + year +
                ", nsong=" + nsong +
                ", hp='" + hp + '\'' +
                '}';
    }

    /*
    public Album(String titolo, int artistaAl, int anno, int nBrani, String caDi) {
        this.titolo = titolo;
        this.artistaAl = artistaAl;
        this.anno = anno;
        this.nBrani = nBrani;
        this.caDi = caDi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(idAl, album.idAl) && Objects.equals(titolo, album.titolo) && Objects.equals(artistaAl, album.artistaAl) && Objects.equals(anno, album.anno) && Objects.equals(nBrani, album.nBrani) && Objects.equals(caDi, album.caDi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAl, titolo, artistaAl, anno, nBrani, caDi);
    }

    @Override
    public String toString() {
        return "album{" +
                "idAl=" + idAl +
                ", titolo='" + titolo + '\'' +
                ", artista='" + artistaAl + '\'' +
                ", anno=" + anno +
                ", nBrani=" + nBrani +
                ", caDi='" + caDi + '\'' +
                '}';
    }

     */

}
