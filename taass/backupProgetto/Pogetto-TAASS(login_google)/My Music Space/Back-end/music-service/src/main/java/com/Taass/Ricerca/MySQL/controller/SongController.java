package com.Taass.Ricerca.MySQL.controller;


import com.Taass.Ricerca.MySQL.model.Song;
import com.Taass.Ricerca.MySQL.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/music/song")

public class SongController {
    @Autowired
    private final SongService service;

    public SongController(SongService service) {
        this.service = service;
    }

    @GetMapping
    List<Song> getSong(){return service.getSong();}

    @PostMapping
    Song createSong(@RequestBody Song song){return service.createSong(song);}

    @PutMapping("/{songId}/album/{albumId}")
    Song assignAlbumToSong(
            @PathVariable Long songId,
            @PathVariable Long albumId
    ){return service.assignAlbumToSong(songId, albumId);}

}