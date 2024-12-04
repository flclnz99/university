package com.Taass.Ricerca.MySQL.songs;

import com.Taass.Ricerca.MySQL.album.Album;
import com.Taass.Ricerca.MySQL.artists.Artist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "Canzoni")
public class Song {

    @Id
    @SequenceGenerator(
            name = "canzoni_id_sequence",
            sequenceName= "canzoni_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "canzoni_id_sequence"
    )
    @Column (nullable = false)
    private Long id;
    private String title;


    @JsonIgnore
    @ManyToMany(mappedBy = "wroteSongs")
    private Set<Artist> artists = new HashSet<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="song_album")
    private Album album;

    private int year;
    private int duration;
    private String feat;

    public Song() {}

    public Long getIdSong() {return this.id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return this.title;}

    public void setTitle(String title) {this.title = title;}

    public Album getAlbum() {return this.album;}

    public void setAlbum(Album album) {this.album = album;}

    //public String getArtist() {return this.artist;}

    //public void setArtist(String artist) {this.artist = artist;}

    public int getYear() {return this.year;}

    public void setYear(int year) {this.year = year;}

    public int getDuration() {return this.duration;}

    public void setDuration(int durata) {this.duration=durata;}

    public String getFeat() {return this.feat;}

    public void setFeat(String feat) {this.feat = feat;}

    public Set<Artist> getArtists() {return this.artists;}

    public void assignArtist(Artist artist) {this.artists.add(artist);}

    public void assignAlbum(Album album) {this.album=album;}

    /*
    public Song(String titolo, int albumc, int artistac, int anno, int durata, String feat) {
        //this.idC = idc;
        this.titolo = titolo;
        this.albumC = albumc;
        this.artistaC = artistac;
        this.anno = anno;
        this.durata = durata;
        this.feat = feat;
    }
    */

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song canzoni = (Song) o;
        return Objects.equals(idC, canzoni.idC) && Objects.equals(titolo, canzoni.titolo) && Objects.equals(albumC, canzoni.albumC) && Objects.equals(artistaC, canzoni.artistaC) && Objects.equals(anno, canzoni.anno) && Objects.equals(durata, canzoni.durata) && Objects.equals(feat, canzoni.feat);
    }


    @Override
    public int hashCode() {
        return Objects.hash(idC, titolo, albumC, artistaC, anno, durata, feat);
    }

    @Override
    public String toString() {
        return "Canzoni{" +
                "IdC=" + idC +
                ", Titolo='" + titolo + '\'' +
                ", AlbumC='" + albumC + '\'' +
                ", ArtistaC='" + artistaC + '\'' +
                ", Anno=" + anno +
                ", Durata=" + durata +
                ", Feat='" + feat + '\'' +
                '}';
    }
    */

    @Override
    public String toString() {
        return "Song{" +
                "titolo='" + title + '\'' +
                ", albumC=" + album +
                //", artistaC=" + artist +
                ", anno=" + year +
                ", durata=" + duration +
                ", feat='" + feat + '\'' +
                ", artists=" + artists +
                '}';
    }
}
