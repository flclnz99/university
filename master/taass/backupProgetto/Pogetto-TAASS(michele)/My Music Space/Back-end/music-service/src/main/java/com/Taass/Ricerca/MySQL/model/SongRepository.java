package com.Taass.Ricerca.MySQL.model;

import com.Taass.Ricerca.MySQL.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long>{
}
