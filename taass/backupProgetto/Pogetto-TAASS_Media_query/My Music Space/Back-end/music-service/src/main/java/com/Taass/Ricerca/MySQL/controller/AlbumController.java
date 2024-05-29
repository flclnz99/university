package com.Taass.Ricerca.MySQL.controller;

import com.Taass.Ricerca.MySQL.service.AlbumService;
import com.Taass.Ricerca.MySQL.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")

public class AlbumController {

    @Autowired
    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @GetMapping
    List<Album> getAlbum(){return service.getAlbum();}

    @PostMapping
    Album postAlbum(@RequestBody Album album){return service.createAlbum(album);}

    @PutMapping("/{albumId}/artist/{artistId}")
    Album putAlbum(
            @PathVariable Long albumId,
            @PathVariable Long artistId
    ){return service.assignArtistToAlbum(albumId,artistId);}
}
