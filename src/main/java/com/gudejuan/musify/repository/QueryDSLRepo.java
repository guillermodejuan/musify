package com.gudejuan.musify.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.gudejuan.musify.entity.User;
import com.gudejuan.musify.util.Constantes;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gudejuan.musify.entity.Artist;
import com.gudejuan.musify.entity.ArtistArtist;
import com.gudejuan.musify.entity.ArtistStyle;
import com.gudejuan.musify.entity.QArtist;
import com.gudejuan.musify.entity.QArtistArtist;
import com.gudejuan.musify.entity.QArtistStyle;
import com.gudejuan.musify.entity.QStyle;
import com.gudejuan.musify.entity.QUser;
import com.gudejuan.musify.entity.Style;

@Repository("queryDSLRepo")
public class QueryDSLRepo {

	private QUser qUser = QUser.user;
	private QArtistStyle qArtistStyle = QArtistStyle.artistStyle;
	private QArtist qArtist = QArtist.artist;
	private QStyle qStyle = QStyle.style;
	private QArtistArtist qArtistArtist = QArtistArtist.artistArtist;

	@Autowired
	@Qualifier("artistJpaRepository")
	private ArtistJpaRepository artistJpaRepository;
	
	@Autowired
	@Qualifier("artistArtistJpaRepository")
	private ArtistArtistJpaRepository artistArtistJpaRepository;
	
	

	@PersistenceContext
	private EntityManager em;

	public List<User> findUserByName(String name) {
		JPAQuery<User> query = new JPAQuery<User>(em);
		List<User> users = query.select(qUser).from(qUser).where(qUser.name.equalsIgnoreCase(name)).fetch();
		return users;
	}

	public List<Artist> findArtistByStyle(String stylename) {
		
		JPAQuery<Style> query0 = new JPAQuery<Style>(em);
		Style style = query0.select(qStyle).from(qStyle).where(qStyle.name.equalsIgnoreCase(stylename)).fetchOne();
				
		JPAQuery<ArtistStyle> query1 = new JPAQuery<ArtistStyle>(em);
		List<ArtistStyle> artistStyles = query1.select(qArtistStyle).from(qArtistStyle)
				.where(qArtistStyle.styelId.eq(style.getId())).fetch();
				
		List artists = new ArrayList<Artist>();
		for (ArtistStyle as : artistStyles) {
			JPAQuery<Artist> query2 = new JPAQuery<Artist>(em);
			Artist artist = query2.select(qArtist).from(qArtist).where(qArtist.id.eq(as.getArtistId())).fetchOne();
			artists.add(artist);
			System.out.println(artist.toString());
		}
		return artists;
				
	}

	public Artist findArtistById(int id) {
		JPAQuery<Artist> query = new JPAQuery<Artist>(em);
		Artist artist = query.select(qArtist).from(qArtist).where(qArtist.id.eq(id)).fetchOne();
		return artist;
	}

	public List<Artist> getArtistCollaborators(Artist artist) {
		JPAQuery<ArtistArtist> query = new JPAQuery<ArtistArtist>(em);
		List<ArtistArtist> collaborations = query.select(qArtistArtist).from(qArtistArtist).where(qArtistArtist.idArtist.eq(artist.getId())).fetch();
		
		List<Artist> collaborators = new ArrayList<Artist>();
		for (ArtistArtist aa : collaborations) {
			JPAQuery<Artist> subquery = new JPAQuery<Artist>(em);
			Artist collaborator = subquery.select(qArtist).from(qArtist).where(qArtist.id.eq(aa.getIdArtist1())).fetchOne();
			collaborators.add(collaborator);
		}
		return collaborators;
	}

	public List<Artist> getArtistNonCollaborators(List<Artist> collaborators, int artistId) {
		
		List<Artist> nonCollaborators = new ArrayList<Artist>();
		List<Artist> allArtists = artistJpaRepository.findAll();
		for (Artist artist : allArtists) {
			boolean noncollab = true;
			for (Artist collab : collaborators) {
				if (collab.getId() == artist.getId()) {
					noncollab = false;
				}
			}
			if (noncollab && artist.getId() != artistId) {
				nonCollaborators.add(artist);
			}
		}
		return nonCollaborators;
	}

	public void removeCollab(int artistId, int collabId) {
		JPAQuery<ArtistArtist> query = new JPAQuery<ArtistArtist>(em);
		ArtistArtist collaboration = query.select(qArtistArtist).from(qArtistArtist)
				.where(qArtistArtist.idArtist.eq(artistId).and(qArtistArtist.idArtist1.eq(collabId))).fetchOne();
		
		artistArtistJpaRepository.delete(collaboration);;
		
	}

	
}
