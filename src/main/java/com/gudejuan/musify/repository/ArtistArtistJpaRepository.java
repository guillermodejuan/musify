package com.gudejuan.musify.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gudejuan.musify.entity.Artist;
import com.gudejuan.musify.entity.ArtistArtist;

@Repository("artistArtistJpaRepository")
public interface ArtistArtistJpaRepository extends JpaRepository<ArtistArtist, Serializable>{

}
