package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upp.backend.model.Book;
import upp.backend.model.User;
import upp.backend.model.Works;
import upp.backend.repository.BookRepository;
import upp.backend.service.UserService;
import upp.backend.service.WorksService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Component
public class SaveBookAndCheckPlagiarism implements JavaDelegate {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    WorksService worksService;
    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("Save pdf to dir");
        System.out.println("PATHHHHHHHHHH" + (String)delegateExecution.getVariable("pdf"));
        Map<String,Object> formVariables = delegateExecution.getVariables();
        String bookName = formVariables.get("name").toString();
        String username = formVariables.get("currentUser").toString();
        String pdfs = formVariables.get("pdf").toString();
        List<String> pdfList = Arrays.asList(pdfs.split(","));
        System.out.println("pdfList "+ pdfList);
        User user = userService.findByUsername(username);
        Book book = bookRepository.findBookByName(bookName);
        book.setPrice(Double.parseDouble(formVariables.get("price").toString()));
        book.setNumOfPages(Integer.parseInt(formVariables.get("numOfPages").toString()));
        book.setAuthorId(user.getId().toString());
        book.setPublisherId("1");
        book.setYearOfIssue(Date.valueOf(LocalDate.now()));
        bookRepository.save(book);
        for (String pdf : pdfList) {
            System.out.println("pdfList "+ pdf);
            String toSplit = pdf;
            String[] parts = toSplit.split("/");
            System.out.print("42 ===>"+parts);
            Works works = new Works();
            works.setFilePath(pdf);
            works.setFileName(parts[parts.length-1]);
            works.setUser(user);

            worksService.save(works);
        }
    }
}
