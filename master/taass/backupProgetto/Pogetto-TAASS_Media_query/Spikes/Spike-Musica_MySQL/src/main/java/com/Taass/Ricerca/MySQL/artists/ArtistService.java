package com.Taass.Ricerca.MySQL.artists;

import com.Taass.Ricerca.MySQL.songs.Song;
import com.Taass.Ricerca.MySQL.songs.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
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

    List<Artist> getArtist(){return artistRepository.findAll();}

    Artist createArtist(Artist artist){return artistRepository.save(artist);}

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
