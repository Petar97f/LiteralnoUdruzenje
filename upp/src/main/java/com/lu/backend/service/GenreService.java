package com.lu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lu.backend.model.Genre;
import com.lu.backend.repository.GenreRepository;



@Service
public class GenreService {
	
	@Autowired
    private GenreRepository genreRepository;
	
   public List<Genre> findall() {
	   return genreRepository.findAll();
   }
}
