package com.gudejuan.musify.service;

import java.util.List;

import com.gudejuan.musify.entity.Style;

public interface StylesService {
	
	public abstract List<Style>listStyles();
	public abstract Style addStyle(Style style);
	public abstract void removeStyle(Style style);
	public abstract Style updateArtist(Style style);

}
