package com.gudejuan.musify.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gudejuan.musify.entity.ArtistStyle;
import com.gudejuan.musify.repository.ArtistStyleJpaRepository;
import com.gudejuan.musify.repository.StylesJpaRepository;
import com.gudejuan.musify.service.ArtistStyleService;

@Service("artistStylesServiceImpl")
public class ArtistStyleServiceImpl implements ArtistStyleService {

	@Autowired
	@Qualifier("artistStylesJpaRepository")
	private ArtistStyleJpaRepository artistStylesJpaRepository;
	
	@Override
	public List<ArtistStyle> listAllArtistStyles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArtistStyle addArtistStyle(ArtistStyle artistStyle) {
		artistStylesJpaRepository.save(artistStyle);
		return null;
	}

	@Override
	public void removeArtistStyle(ArtistStyle artistStyle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArtistStyle updateArtistStyle(ArtistStyle artistStyle) {
		// TODO Auto-generated method stub
		return null;
	}

}
