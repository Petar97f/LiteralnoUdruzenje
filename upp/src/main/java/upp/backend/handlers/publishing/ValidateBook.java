package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.Book;
import upp.backend.model.FormFieldDTO;
import upp.backend.repository.BookRepository;

import java.util.List;

@Service
@Component
public class ValidateBook implements JavaDelegate {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormFieldDTO> dtos=(List<FormFieldDTO>) delegateExecution.getVariable("BookForm");
        System.out.println(dtos);
        String title=dtos.get(0).getFieldValue();
        Book book=bookRepository.findBookByName(title);
        if(book==null){
            delegateExecution.setVariable("validated",true);
        }else{
            delegateExecution.setVariable("validated",false);
        }
    }
}
