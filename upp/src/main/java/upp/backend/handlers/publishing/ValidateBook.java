package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.Book;
import upp.backend.model.FormFieldDTO;
import upp.backend.model.User;
import upp.backend.repository.BookRepository;
import upp.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Component
public class ValidateBook implements JavaDelegate {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormFieldDTO> dtos=(List<FormFieldDTO>) delegateExecution.getVariable("BookForm");
        Map<String, Object> formVariables = delegateExecution.getVariables();
        System.out.println(formVariables);
        boolean isValid =true;


        if (formVariables.get("name") == null ) {
            isValid =false;
        }else if (formVariables.get("synopsis") == null ) {
            isValid = false;
        }
        if (formVariables.get("genres") != null) {
            String genres = formVariables.get("genres").toString();
            String str[] = genres.split(",");
            List<String> genresListIds = new ArrayList<String>();
            genresListIds = Arrays.asList(str);
            if (genresListIds.isEmpty()) {
                System.out.println("ovdeee2");
                isValid = false;
            }
        }


        Book book=bookRepository.findBookByName(formVariables.get("name").toString());
        if(book==null){
            isValid = true;
        }else{
            isValid = false;
        }

        User u=userRepository.findByUsername(formVariables.get("writer").toString());

        delegateExecution.setVariable("validated",isValid);
        delegateExecution.setVariable("currentUser",u.getUsername());
    }
}
