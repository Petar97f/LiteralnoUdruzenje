package upp.backend.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import upp.backend.model.Works;
import upp.backend.repository.WorksRepository;

@Service
public class WorksService {

	@Autowired
    private WorksRepository worksRepository;
	
	private final Path root = Paths.get("uploads");
	
	public String upload(MultipartFile file,String username, String processInstanceId) {
		String fileName = file.getOriginalFilename();
		System.out.println("upload method"+fileName);
		
		if(!Files.exists(Paths.get("uploads/"+username))) {
			try {
				Files.createDirectory(Paths.get("uploads/"+username));
		    } catch (IOException e) {
		    	e.printStackTrace();
		        throw new RuntimeException("Could not initialize folder for upload!");
		     }
		}
		
		if(!Files.exists(Paths.get("uploads/"+username+"/" + processInstanceId))) {
			try {
				Files.createDirectory(Paths.get("uploads/"+username+"/" + processInstanceId));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
	      Files.copy(file.getInputStream(), Paths.get("uploads/"+username+"/" + processInstanceId).resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
				.path(username + "/" + processInstanceId+"/"+fileName).toUriString();
		System.out.println("Filename56"+fileDownloadUri);
		return fileDownloadUri;
	}
	
	public Resource downloadFile(String processId, String username, String fileName) {
		System.out.println("downloadFile"+ username + fileName);
		Path path = Paths.get("uploads/"+ username + "/" + processId + "/" + fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(resource.getFilename());
		return resource;
	}
	
	public Works save(Works works) {
		return worksRepository.save(works);
	}
	
}
