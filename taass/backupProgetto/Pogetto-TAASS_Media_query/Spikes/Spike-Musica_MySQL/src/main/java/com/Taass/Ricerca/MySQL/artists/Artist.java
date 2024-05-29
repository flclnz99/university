package com.Taass.Ricerca.MySQL.artists;

import com.Taass.Ricerca.MySQL.album.Album;
import com.Taass.Ricerca.MySQL.songs.Song;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "Artista")
public class Artist {
    @Id
    @SequenceGenerator(
            name = "artista_id_sequence",
            sequenceName= "artista_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "artista_id_sequence"
    )
    @Column (nullable = false)
    private Long id;
    private String name;


    public Artist(){}

    @ManyToMany
    @JoinTable(
            name="artists_songs",
            joinColumns = @JoinColumn(name="idArtist"),
            inverseJoinColumns = @JoinColumn(name="idSong")
    )
    private Set<Song> wroteSongs = new HashSet<>();

    @OneToMany(mappedBy="artist")
    private Set<Album> albums = new HashSet<>();

    public Long getIdArtist() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Set<Album> getAlbum() {return this.albums;}

    //public void setAlbum(Album album) {this.albums.add(album);}

    //per cardinalità
    public Set<Song> getSongs() {return this.wroteSongs;}
    public void assignSong(Song song) {wroteSongs.add(song);}

    public void assignAlbum(Album album) {this.albums.add(album);}

    //public int getSongs() {return this.songs;}

    //public void setSongs(int songs) {this.songs = songs;}

    /*
    public Artist(Long id, String name, int album, int songs){
        this.id = id;
        name = name;
        album = album;
        songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artista = (Artist) o;
        return Objects.equals(id, artista.id) && Objects.equals(name, artista.name) && Objects.equals(album, artista.album) && Objects.equals(songs, artista.songs) ;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, album, songs);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", songs='" + songs + '\'' +
                '}';
    }
    */
}


