package com.gudejuan.musify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="artist")
public class Artist {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Size(min = 2)
	@Column(name="name")
	private String name;
	@Column(name="year")
	private int year;
	
	
	
	public Artist() {
		super();
	}
	public Artist(int id, String name, int year) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", year=" + year + "]";
	}
	
	
	
	
}
