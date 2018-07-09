package com.gudejuan.musify.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gudejuan.musify.entity.Artist;
import com.gudejuan.musify.entity.ArtistArtist;
import com.gudejuan.musify.entity.Style;
import com.gudejuan.musify.repository.ArtistArtistJpaRepository;
import com.gudejuan.musify.repository.ArtistJpaRepository;
import com.gudejuan.musify.repository.QueryDSLRepo;
import com.gudejuan.musify.service.ArtistService;
import com.gudejuan.musify.util.Constantes;

@Service("artistServiceImpl")
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	@Qualifier("artistJpaRepository")
	private ArtistJpaRepository artistJpaRepository;
	
	@Autowired
	@Qualifier("queryDSLRepo")
	private QueryDSLRepo queryDSLRepo;
	
	@Autowired
	@Qualifier("artistArtistJpaRepository")
	private ArtistArtistJpaRepository artistArtistJpaRepository;
	
	@Override
	public List<Artist> listAllArtists(String style) {
		if (style.equals(Constantes.STYLE_NULL)) {
			return artistJpaRepository.findAll();
		} else {
			return queryDSLRepo.findArtistByStyle(style);
		}
	}

	@Override
	public Artist addArtist(Artist artist) {
		return artistJpaRepository.save(artist);
	}

	@Override
	public void removeArtist(Artist artist) {
		artistJpaRepository.delete(artist);
	}

	@Override
	public Artist updateArtist(Artist artist) {
		return artistJpaRepository.save(artist);
	}

	@Override
	public Artist getArtistById(int id) {
		return queryDSLRepo.findArtistById(id);
	}

	@Override
	public List<Artist> getCollaborators(Artist artist) {
		return queryDSLRepo.getArtistCollaborators(artist);
	}

	@Override
	public List<Artist> getNonCollaborators(List<Artist> collaborators, int artistId) {
		return queryDSLRepo.getArtistNonCollaborators(collaborators, artistId);
		
	}

	@Override
	public void removeCollab(int artistId, int collabId) {
		queryDSLRepo.removeCollab(artistId,collabId);
		
	}

	@Override
	public void addCollab(int artistId, int collabId) {
		ArtistArtist collab = new ArtistArtist();
		collab.setIdArtist(artistId);
		collab.setIdArtist1(collabId);
		artistArtistJpaRepository.save(collab);
		
	}
	
}
