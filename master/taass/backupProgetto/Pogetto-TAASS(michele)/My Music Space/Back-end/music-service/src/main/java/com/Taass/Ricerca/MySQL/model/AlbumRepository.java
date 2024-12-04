package com.Taass.Ricerca.MySQL.model;

import com.Taass.Ricerca.MySQL.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
