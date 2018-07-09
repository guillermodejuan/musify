package com.gudejuan.musify.service;

import java.util.List;

import com.gudejuan.musify.entity.Artist;
import com.gudejuan.musify.entity.ArtistArtist;
import com.gudejuan.musify.entity.Style;

public interface ArtistArtistService {

	public abstract List<ArtistArtist>listAllArtistArtists();
	public abstract ArtistArtist addArtistArtist(ArtistArtist artistArtist);
	public abstract void removeArtistArtist(ArtistArtist artistArtist);
	public abstract ArtistArtist updateArtistArtist(ArtistArtist artistArtist);
}