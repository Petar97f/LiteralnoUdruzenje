package upp.backend.handlers.publishing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import upp.backend.model.Book;
import upp.backend.model.Works;
import upp.backend.repository.BookRepository;
import upp.backend.repository.WorksRepository;

import java.util.Map;

@Component
@Service
public class SaveNewBook implements JavaDelegate {
    @Autowired
    WorksRepository worksRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String,Object> formVariables = delegateExecution.getVariables();

        Works works = worksRepository.findByFileName(formVariables.get("pdf").toString());



            Works workse = new Works();
            workse.setFilePath(formVariables.get("NewManuscriptId").toString());
            workse.setFileName(formVariables.get("NewManuscriptId").toString());
            workse.setUser(works.getUser());

            worksRepository.save(workse);

            worksRepository.delete(works);

    }
}
