package com.lu.backend.service;

import com.lu.backend.dto.GenreDTO;
import com.lu.backend.model.Genre;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RegisterService implements TaskListener {

    @Autowired
    GenreService genreService;

    @Autowired
    TaskService taskService;
    @Autowired
    FormService formService;


    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("Kreiran prvi task");

        List<Genre> genres = genreService.findall();
        ArrayList<GenreDTO> genresDTO = new ArrayList<>();
        for(Genre g: genres) {
            genresDTO.add(genreService.convertToDTO(g));
        }
        String taskId = delegateTask.getId();
        FormService formService = delegateTask.getProcessEngineServices().getFormService();
        TaskFormData taskFormData = formService.getTaskFormData(taskId);

        List<FormField> properties = taskFormData.getFormFields();
        System.out.println(properties);

//        for (FormField f:properties
//             ) {
//            if(f.getId().equals("genres")){
//                f.setV
//            }
//        }
//        properties.add()

        DelegateExecution execution = delegateTask.getExecution();
        execution.setVariable("genres", genresDTO);
    }
}
