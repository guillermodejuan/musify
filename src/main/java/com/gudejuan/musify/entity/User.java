package com.gudejuan.musify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.gudejuan.musify.util.Constantes;

import java.util.Arrays;
import java.util.Calendar;

@Entity
@Table(name="people")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Pattern(regexp=".+@.+\\.[a-z]+", message = "El email introducido no es válido")
	@Column(name="name")
	private String name;
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, al menos un dígito, al menos una minúscula, al menos una mayúscula y al menos un caracter no alfanumérico.")
	@Column(name="password")
	private String password;
	
		
	@Digits(integer = 4, fraction = 0)
	@Column(name="years")
	private int year;
	
	@Column(name="rol")
	private int rol;
	
	public User() {
		super();
		
	}
	
	

	public User(int id, @Pattern(regexp = ".+@.+\\.[a-z]+", message = "El email introducido no es válido") String name,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, al menos un dígito, al menos una minúscula, al menos una mayúscula y al menos un caracter no alfanumérico.") String password,
			@Digits(integer = 4, fraction = 0) int year, int rol) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.year = year;
		this.rol = rol;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", year=" + year + ", rol=" + rol + "]";
	}

	

	
	
	

}
