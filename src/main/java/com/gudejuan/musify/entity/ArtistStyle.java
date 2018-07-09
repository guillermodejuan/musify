package com.gudejuan.musify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table (name="artist_styles", uniqueConstraints = @UniqueConstraint(columnNames = {"artist_id", "styles_id"}))
public class ArtistStyle {
	
	@Column(name = "artist_id")
	private int artistId;
	@Column(name = "styles_id")
	private int styelId;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	
	
	public ArtistStyle() {
		super();
	}
	public ArtistStyle(int artistId, int styelId) {
		super();
		this.artistId = artistId;
		this.styelId = styelId;
	}
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public int getStyelId() {
		return styelId;
	}
	public void setStyelId(int styelId) {
		this.styelId = styelId;
	}
	
	
	
	
	
}
