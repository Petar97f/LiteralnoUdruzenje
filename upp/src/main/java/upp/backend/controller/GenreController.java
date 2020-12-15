package upp.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import upp.backend.dto.GenreDTO;
import upp.backend.model.Genre;
import upp.backend.service.GenreService;
import upp.backend.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class GenreController {

	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private GenreService genreService;
	
	 @GetMapping(value = "/getGenres")
	 public ArrayList<GenreDTO> getGenre() {
		List<Genre> genres = genreService.findall();
    	ArrayList<GenreDTO> genresDTO = new ArrayList<>();
    	for(Genre g: genres) {
    		genresDTO.add(genreService.convertToDTO(g));
    	}
    	return genresDTO;
	}
	
}
