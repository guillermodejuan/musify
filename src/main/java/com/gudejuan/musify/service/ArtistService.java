package com.gudejuan.musify.service;

import java.util.List;

import com.gudejuan.musify.entity.Artist;
import com.gudejuan.musify.entity.Style;

public interface ArtistService {

	public abstract List<Artist>listAllArtists(String style);
	public abstract Artist addArtist(Artist artist);
	public abstract void removeArtist(Artist artist);
	public abstract Artist updateArtist(Artist artist);
	public abstract Artist getArtistById(int id);
	public abstract List<Artist> getCollaborators(Artist artist);
	public abstract List<Artist> getNonCollaborators(List<Artist> collaborators, int artistId);
	public abstract void removeCollab(int artistId, int collabId);
	public abstract void addCollab(int artistId, int collabId);
}
