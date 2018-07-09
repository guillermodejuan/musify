package com.gudejuan.musify.service;

import java.util.List;

import com.gudejuan.musify.entity.Artist;
import com.gudejuan.musify.entity.User;

public interface PeopleService {
	
	public abstract List<User>listUsers(String name);
	public abstract User addUser(User user);
	public abstract void removeUser(User user);
	public abstract User updateArtist(User user);
	

}
