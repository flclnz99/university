package com.Taass.Ricerca.MySQL.service;

import com.Taass.Ricerca.MySQL.model.Album;
import com.Taass.Ricerca.MySQL.model.AlbumRepository;
import com.Taass.Ricerca.MySQL.model.Artist;
import com.Taass.Ricerca.MySQL.model.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service

public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    public List<Album> getAlbum() {return albumRepository.findAll();}

    public Album createAlbum(Album album) {return albumRepository.save(album);}

    public Album assignArtistToAlbum(Long albumId, Long artistId) {
        try {
            Album album = albumRepository.findById(albumId).get();
            Artist artist = artistRepository.findById(artistId).get();

            if(!isLinked(artist, album)){
                album.assignArtist(artist);
                artistRepository.save(artist);
                return album;
            }
            System.out.println("album " + album.getTitle() +
                    " is yet linked to "+artist.getName());
            return null;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isLinked(Artist artist, Album album) {return (album.getArtist() == artist);}
}
