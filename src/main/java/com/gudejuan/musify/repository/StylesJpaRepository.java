package com.gudejuan.musify.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gudejuan.musify.entity.Style;

@Repository("stylesJpaRepository")
public interface StylesJpaRepository extends JpaRepository<Style, Serializable> {

}
