package com.cinemaspring.dao;

import java.util.List;

import com.cinemaspring.model.Film;

public interface FilmDAO {
	
	public void insert(Film f);
	public void delete(int id);
	public List <Film> findByRegista(String regista);

}
