package com.Taass.Ricerca.MySQL.service;

import com.Taass.Ricerca.MySQL.model.Album;
import com.Taass.Ricerca.MySQL.model.AlbumRepository;
import com.Taass.Ricerca.MySQL.model.ArtistRepository;

import com.Taass.Ricerca.MySQL.model.Song;
import com.Taass.Ricerca.MySQL.model.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class SongService {
    @Autowired
    SongRepository songRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    ArtistRepository artistRepository;

    private boolean isSongInAlbum(Song song, Album album) {return (album.getSongs().contains(song));}
    //private boolean isSongInArtist(Song song, Artist artist) {return (artist.getSongs().contains(song));}

    public List<Song> getSong() {return songRepository.findAll();}

    public Song createSong(Song song) {return songRepository.save(song);}
/*
    public Artist assignSongToArtist(Long artistId, Long songId) {
        try {
            Artist artist = artistRepository.findById(artistId).get();
            Song song = songRepository.findById(songId).get();

            if(!isSongInArtist(song, artist)){
                artist.assignSong(song);
                artistRepository.save(artist);
                return artist;
            }
            System.out.println("artist " + artist.getName() +
                    " is yet linked to "+song.getTitle());
            return null;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }

 */

    public Song assignAlbumToSong(Long songId, Long albumId) {
        try {
            Album album = albumRepository.findById(albumId).get();
            Song song = songRepository.findById(songId).get();

            if(!isSongInAlbum(song, album)){
                song.assignAlbum(album);
                songRepository.save(song);
                return song;
            }
            System.out.println("album " + album.getTitle() +
                    " is yet linked to "+song.getTitle());
            return null;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }
}
