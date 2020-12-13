package com.lu.backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lu.backend.dto.GenreDTO;
import com.lu.backend.model.Genre;
import com.lu.backend.service.GenreService;

@Service
public class RegisterService implements TaskListener {

	@Autowired
	private GenreService genreService;

    @Autowired
    TaskService taskService;
    @Autowired
    FormService formService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void notify(DelegateTask delegateTask) {
	        List<Genre> genres = genreService.findall();
	        ArrayList<GenreDTO> genresDTO = new ArrayList<>();
	        for(Genre g: genres) {
	            genresDTO.add(genreService.convertToDTO(g));
	        }
	       
	        String taskId = delegateTask.getId();
	        System.out.println("genresDTO: "+ genresDTO);
	    
	        FormService formService = delegateTask.getProcessEngineServices().getFormService();
	        TaskFormData taskFormData = formService.getTaskFormData(taskId);

	        List<FormField> properties = taskFormData.getFormFields();
	 
	        
	        if (properties != null) {

				for (FormField field : properties) {
					if (field.getId().equals("genres")) {
						
						HashMap<String, String> items = (HashMap<String, String>) field.getType().getInformation("values");
						
						for(GenreDTO g: genresDTO) {
							String valueId = g.getId().toString();
						    String valueName = g.getName();
						    System.out.println(valueId + valueName);
						    items.put(valueId, valueName);
				        }
						System.out.println(items);
					}
				}
	        }
	}

}
