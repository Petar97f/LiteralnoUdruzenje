package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.dto.BetaReaderDTO;
import upp.backend.dto.GenreDTO;
import upp.backend.model.Genre;
import upp.backend.model.User;
import upp.backend.model.UserRole;
import upp.backend.repository.UserRepository;
import upp.backend.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Component
public class BetaReadersListener implements TaskListener {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Override
    public void notify(DelegateTask delegateTask) {
        List<User> users = userRepository.findAllByUserRole(UserRole.BETA_READER);
        List<BetaReaderDTO> readerDTOS = new ArrayList<>();

        for (User u:users
             ) {
            readerDTOS.add(userService.convertToBetaReaderDTO(u));
        }
        String taskId = delegateTask.getId();
        System.out.println("readersDTO: "+ readerDTOS);

        FormService formService = delegateTask.getProcessEngineServices().getFormService();
        TaskFormData taskFormData = formService.getTaskFormData(taskId);

        List<FormField> properties = taskFormData.getFormFields();

        if (properties != null) {

            for (FormField field : properties) {
                if (field.getId().equals("betaReadersId")) {

                    HashMap<String, String> items = (HashMap<String, String>) field.getType().getInformation("values");

                    for(BetaReaderDTO g:readerDTOS) {
                        String valueId = g.getId().toString();
                        String valueName = g.getUsername();
                        System.out.println(valueId + valueName);
                        items.put(valueId, valueName);
                    }
                    System.out.println(items);
                }
            }
        }
    }
}
