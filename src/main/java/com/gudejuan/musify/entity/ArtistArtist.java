package com.gudejuan.musify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "artist_artist", uniqueConstraints = @UniqueConstraint(columnNames = {"artist_id", "artist_id1"}))
public class ArtistArtist {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "artist_id")
	private int idArtist;
	
	@Column(name = "artist_id1")
	private int idArtist1;

	public ArtistArtist() {
		super();
	}

	public ArtistArtist(int id, int idArtist, int idArtist1) {
		super();
		this.id = id;
		this.idArtist = idArtist;
		this.idArtist1 = idArtist1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdArtist() {
		return idArtist;
	}

	public void setIdArtist(int idArtist) {
		this.idArtist = idArtist;
	}

	public int getIdArtist1() {
		return idArtist1;
	}

	public void setIdArtist1(int idArtist1) {
		this.idArtist1 = idArtist1;
	}

	@Override
	public String toString() {
		return "ArtistArtist [id=" + id + ", idArtist=" + idArtist + ", idArtist1=" + idArtist1 + "]";
	}
	
	
}
