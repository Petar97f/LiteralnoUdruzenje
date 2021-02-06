package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.Book;
import upp.backend.model.BookComments;
import upp.backend.repository.BookRepository;
import upp.backend.repository.UserRepository;
import upp.backend.service.EmailService;

import java.util.List;
import java.util.Map;

@Service
@Component
public class SendComments implements JavaDelegate {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String,Object> formVariables = delegateExecution.getVariables();
        Book book = bookRepository.findBookByName(formVariables.get("name").toString());
        List<BookComments> bookCommentsList = book.getComments();
        //poslati mejl
        String text="";
        for (BookComments b:bookCommentsList
             ) {
            text += " " +b.getComment();
        }
        emailService.sendEmail("Comments for your book",userRepository.findUserById(Long.parseLong(book.getAuthorId())),text);
    }
}
