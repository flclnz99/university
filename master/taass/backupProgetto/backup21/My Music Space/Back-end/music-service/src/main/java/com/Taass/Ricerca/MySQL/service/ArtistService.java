package com.Taass.Ricerca.MySQL.service;

import com.Taass.Ricerca.MySQL.model.Artist;
import com.Taass.Ricerca.MySQL.model.ArtistRepository;
import com.Taass.Ricerca.MySQL.model.Song;
import com.Taass.Ricerca.MySQL.model.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ArtistService {
    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    private boolean isSongAvailable(Song song, Artist artist) {
        if(artist.getSongs().contains(song)){
            System.out.println("artist " + artist.getName() +
                    " is yet linked to "+song.getTitle() );
            return false;
        }
        if(!song.getArtists().isEmpty()){
            System.out.println("song " + song.getTitle() +
                    " has yet an artist");
            return false;
        }
        return true;
    }

    public List<Artist> getArtist(){return artistRepository.findAll();}

    public Artist createArtist(Artist artist){return artistRepository.save(artist);}

    public Artist assignSongToArtist(Long artistId, Long songId) {
        try {
            Artist artist = artistRepository.findById(artistId).get();
            Song song = songRepository.findById(songId).get();

            if(isSongAvailable(song, artist)){
                artist.assignSong(song);
                artistRepository.save(artist);
                return artist;
            }
            return null;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }
}
