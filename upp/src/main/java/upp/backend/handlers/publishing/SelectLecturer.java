package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.Book;
import upp.backend.model.User;
import upp.backend.model.UserRole;
import upp.backend.repository.BookRepository;
import upp.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

@Service
@Component
public class SelectLecturer implements JavaDelegate {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String,Object> formVariables = delegateExecution.getVariables();
        Book book = bookRepository.findBookByName(formVariables.get("name").toString());
        ArrayList<User> lectures = (ArrayList<User>) userRepository.findAllByUserRole(UserRole.LECTOR);
        Random rand = new Random();
        int randomNumber = rand.nextInt(lectures.size());
        User user = lectures.get(randomNumber);
        book.setLecturer(user);
        bookRepository.save(book);
        delegateExecution.setVariable("lector", user.getUsername());
    }
}
