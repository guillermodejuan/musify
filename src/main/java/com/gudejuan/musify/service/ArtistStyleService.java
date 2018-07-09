package com.gudejuan.musify.service;

import java.util.List;

import com.gudejuan.musify.entity.ArtistStyle;

public interface ArtistStyleService {
	
	public abstract List<ArtistStyle>listAllArtistStyles();
	public abstract ArtistStyle addArtistStyle(ArtistStyle artistStyle);
	public abstract void removeArtistStyle(ArtistStyle artistStyle);
	public abstract ArtistStyle updateArtistStyle(ArtistStyle artistStyle);

}
