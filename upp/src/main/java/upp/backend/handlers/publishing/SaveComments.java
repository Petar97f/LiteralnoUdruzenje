package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.Book;
import upp.backend.model.BookComments;
import upp.backend.model.FormFieldDTO;
import upp.backend.model.User;
import upp.backend.repository.BookRepository;
import upp.backend.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Service
@Component
public class SaveComments implements JavaDelegate {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormFieldDTO> fields = (List<FormFieldDTO>) delegateExecution.getVariable("Beta reader comments");
        Map<String,Object> formVariables = delegateExecution.getVariables();
        String comment = "";
        User u = userRepository.findByUsername(formVariables.get("usernameReader").toString());


        Book book = bookRepository.findBookByName(formVariables.get("name").toString());
        List<BookComments> bookCommentsArrayList = book.getComments();
        for(BookComments bookComments : bookCommentsArrayList) {
            if(bookComments.getBetaReader().getUsername().equals(u.getUsername())) {
                bookComments.setComment(formVariables.get("commentReader").toString());
            }
        }

        book.setComments(bookCommentsArrayList);
        bookRepository.save(book);
    }
}
