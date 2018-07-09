package com.gudejuan.musify.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gudejuan.musify.entity.Style;
import com.gudejuan.musify.repository.StylesJpaRepository;
import com.gudejuan.musify.service.StylesService;

@Service("stylesServiceImpl")
public class StylesServiceImpl implements StylesService{

	@Autowired
	@Qualifier("stylesJpaRepository")
	private StylesJpaRepository stylesJpaRepository;
	
	@Override
	public List<Style> listStyles() {
		return stylesJpaRepository.findAll();
	}

	@Override
	public Style addStyle(Style style) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeStyle(Style style) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Style updateArtist(Style style) {
		// TODO Auto-generated method stub
		return null;
	}

}
