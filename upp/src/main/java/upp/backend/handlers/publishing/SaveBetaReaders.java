package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.Group;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.Book;
import upp.backend.model.BookComments;
import upp.backend.model.User;
import upp.backend.repository.BookCommentsRepository;
import upp.backend.repository.BookRepository;
import upp.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Component
public class SaveBetaReaders implements JavaDelegate {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookCommentsRepository bookCommentsRepository;
    @Autowired
    private IdentityService identityService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String,Object> formVariables = delegateExecution.getVariables();

        Book book= bookRepository.findBookByName(formVariables.get("name").toString());
        System.out.println(formVariables);
        ArrayList<BookComments> bookComments = new ArrayList<>();
        ArrayList<User> betaReadersCollection = new ArrayList<>();

        if (formVariables.get("betaReadersId") != null) {
            String readers = formVariables.get("betaReadersId").toString();
            String str[] = readers.split(",");
            List<String> readersListIds = new ArrayList<String>();
            readersListIds = Arrays.asList(str);
            System.out.println(readersListIds);
            if (!readersListIds.isEmpty()) {
                System.out.println("ovdeee2");
                for (String s:readersListIds
                     ) {
                    User user= userRepository.findUserById(Long.parseLong(s));
                    betaReadersCollection.add(user);
                    BookComments b= new BookComments();
                    b.setBetaReader(user);
                    bookComments.add(b);
                    bookCommentsRepository.save(b);
                }
                book.setComments(bookComments);
                bookRepository.save(book);
            }
            System.out.println(readersListIds);
        }

        List<String> userReaders = new ArrayList<String>();
        for (User u:betaReadersCollection
             ) {
            System.out.println("users ->" + u.getUsername());
            userReaders.add(u.getUsername());
        }

        delegateExecution.setVariable("userReaders", userReaders);
        delegateExecution.setVariable("numberOfReaders", userReaders.size());

    }
}
