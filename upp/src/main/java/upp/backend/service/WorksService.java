package upp.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import upp.backend.model.Works;
import upp.backend.repository.WorksRepository;

@Service
public class WorksService {

	@Autowired
    private WorksRepository worksRepository;
	
	private final Path root = Paths.get("uploads");
	
	public String upload(MultipartFile file,String username, String processInstanceId) {
		String fileName = file.getOriginalFilename();
		
		if(!Files.exists(root)) {
			try {
				Files.createDirectory(root);
		    } catch (IOException e) {
		        throw new RuntimeException("Could not initialize folder for upload!");
		     }
		}
		
		try {
	      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		
		System.out.println("Filename"+fileName);
		return fileName;
	}
	
	public Works save(Works works) {
		return worksRepository.save(works);
	}
	
}
