package com.Taass.Ricerca.MySQL.model;

import com.Taass.Ricerca.MySQL.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
