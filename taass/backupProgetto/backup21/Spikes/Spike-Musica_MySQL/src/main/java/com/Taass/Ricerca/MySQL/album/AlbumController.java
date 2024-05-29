package com.Taass.Ricerca.MySQL.album;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class AlbumController {

    @Autowired
    private final AlbumService service;

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
