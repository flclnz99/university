package com.Taass.Ricerca.MySQL.controller;


import com.Taass.Ricerca.MySQL.service.ArtistService;
import com.Taass.Ricerca.MySQL.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")

public class ArtistController {
    @Autowired
    ArtistService service;

    @GetMapping
    List<Artist> getArtist(){return service.getArtist();}

    @PostMapping
    Artist createArtist(@RequestBody Artist artist){return service.createArtist(artist);}

    @PutMapping("/{artistId}/song/{songId}")
    Artist assignSongToArtist(
            @PathVariable Long artistId,
            @PathVariable Long songId
    ){return service.assignSongToArtist(artistId,songId);}
}