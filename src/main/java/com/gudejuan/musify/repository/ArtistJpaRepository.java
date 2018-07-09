package com.gudejuan.musify.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gudejuan.musify.entity.Artist;


@Repository("artistJpaRepository")
public interface ArtistJpaRepository extends JpaRepository<Artist, Serializable>{

}
