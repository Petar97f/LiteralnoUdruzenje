package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.*;
import upp.backend.repository.BookRepository;
import upp.backend.repository.GenreRepository;
import upp.backend.repository.UserRepository;

import java.util.List;
import java.util.Random;

@Service
@Component
public class SaveAndSelectEditor implements JavaDelegate {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<FormFieldDTO> dtos = (List<FormFieldDTO>) delegateExecution.getVariable("Book details form");
        List<User> editors = userRepository.findAllByUserRole(UserRole.EDITOR);
        Book book = new Book();
        Genre genre = new Genre();
        for(FormFieldDTO f : dtos) {
            if(f.getFieldId().equals("name")) {
                book.setName(f.getFieldValue());
            } else if(f.getFieldId().equals("genre")) {
                book.setGenre(f.getFieldValue());
                genre.setName(f.getFieldValue());
            } else if(f.getFieldId().equals("synopsis")) {
                book.setSynopsis(f.getFieldValue());
            }
        }
        Random rand = new Random();
        int randomNumber = rand.nextInt(editors.size());
        System.out.println("Random broj je " + randomNumber);
        User editor = editors.get(randomNumber);
        book.setEditor(editor);
        bookRepository.save(book);
        genreRepository.save(genre);

        //email fali

    }
}
