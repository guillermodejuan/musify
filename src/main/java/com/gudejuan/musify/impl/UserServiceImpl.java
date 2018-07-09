package com.gudejuan.musify.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gudejuan.musify.entity.Artist;
import com.gudejuan.musify.entity.User;
import com.gudejuan.musify.repository.ArtistJpaRepository;
import com.gudejuan.musify.repository.PeopleJpaRepository;
import com.gudejuan.musify.repository.QueryDSLRepo;
import com.gudejuan.musify.service.PeopleService;

@Service("userServiceImpl")
public class UserServiceImpl implements PeopleService {

	@Autowired
	@Qualifier("peopleJpaRepository")
	private PeopleJpaRepository peopleJpaRepository;
	
	@Autowired
	@Qualifier("queryDSLRepo")
	private QueryDSLRepo queryDSLRepo;
	
	@Override
	public List<User> listUsers(String name) {
		if (name != null) {
			return queryDSLRepo.findUserByName(name);
			
		} else {
			return peopleJpaRepository.findAll();
		}
	}

	@Override
	public User addUser(User user) {
		return peopleJpaRepository.save(user);
	}

	@Override
	public void removeUser(User user) {
		peopleJpaRepository.delete(user);

	}

	@Override
	public User updateArtist(User user) {
		return peopleJpaRepository.save(user);
	}

}
