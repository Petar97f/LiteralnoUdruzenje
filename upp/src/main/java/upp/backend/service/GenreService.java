package upp.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.backend.dto.GenreDTO;
import upp.backend.model.Genre;
import upp.backend.repository.GenreRepository;


@Service
public class GenreService {
	
	@Autowired
    private GenreRepository genreRepository;
	
   public List<Genre> findall() {
	   return genreRepository.findAll();
   }
   public Genre findById(Long id) {
	   return genreRepository.findOneById(id);
	   
	   
   }
   public Genre convertFromDTO(GenreDTO genreDTO){
	   Genre genre = new Genre();
	   if(genre.getId()!=null) {
	   genre.setId(genreDTO.getId());
	   }
	   genre.setName(genreDTO.getName());
	   return genre;
	}
   public GenreDTO convertToDTO(Genre genre){
	   GenreDTO genreDTO = new GenreDTO();
	   genreDTO.setId(genre.getId());
	   genreDTO.setName(genre.getName());
	   return genreDTO;
	}
}
