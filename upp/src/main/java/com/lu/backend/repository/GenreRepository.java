package com.lu.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lu.backend.model.Genre;


public interface GenreRepository extends JpaRepository<Genre,Long> {

	  List<Genre> findAll();
}
