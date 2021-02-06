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

import java.util.*;

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
        Map<String,Object> formVariables = delegateExecution.getVariables();
        List<User> editors = userRepository.findAllByUserRole(UserRole.EDITOR);
        Book book = new Book();
        Genre genre = new Genre();

        book.setName(formVariables.get("name").toString());
        book.setSynopsis(formVariables.get("synopsis").toString());

        if (formVariables.get("genres") != null) {
            String genres = formVariables.get("genres").toString();
            String str[] = genres.split(",");
            List<String> genresListIds = new ArrayList<String>();
            genresListIds = Arrays.asList(str);
            if (genresListIds.isEmpty()) {
                System.out.println("ovdeee2");
            }
            System.out.println(genresListIds);
        }
        Random rand = new Random();
        int randomNumber = rand.nextInt(editors.size());
        System.out.println("Random broj je " + randomNumber);
        User editor = editors.get(randomNumber);
        book.setEditor(editor);

        delegateExecution.setVariable("editor", editor.getUsername());
        bookRepository.save(book);
        genreRepository.save(genre);

        //email fali

    }
}
