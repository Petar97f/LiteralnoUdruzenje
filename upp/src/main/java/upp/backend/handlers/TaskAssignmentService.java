package upp.backend.handlers;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import upp.backend.model.UserRole;
import upp.backend.service.EmailService;
import upp.backend.service.UserDetailsServiceImpl;
import upp.backend.service.UserService;

import java.util.List;

public class TaskAssignmentService implements TaskListener {
    @Autowired
    EmailService emailService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Override
    public void notify(DelegateTask delegateTask) {
        String assignee = delegateTask.getAssignee();
        String taskId = delegateTask.getId();
        List<upp.backend.model.User> users=userDetailsService.findAll();
        for (upp.backend.model.User u:users
             ) {
            if(u.getUserRole()== UserRole.EDITOR){
                delegateTask.setAssignee(u.getUsername());
                break;
            }

        }

        if (assignee != null) {

            // Get User Profile from User Management
            IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
            User user = identityService.createUserQuery().userId(assignee).singleResult();

//            if (user != null) {
//
//                // Get Email Address from User Profile
//                String recipient = user.getEmail();
//
//                if (recipient != null && !recipient.isEmpty()) {
//
//                    try {
//
//                        LOGGER.info(“Task Assignment Email successfully sent to user ‘” + assignee + “‘ with address ‘” + recipient + “‘.”);
//
//                    } catch (Exception e) {
//                        LOGGER.log(Level.WARNING, “Could not send email to assignee”, e);
//                    }
//
//                } else {
//                    LOGGER.warning(“Not sending email to user ” + assignee + “‘, user has no email address.”);
//                }
//
//            } else {
//                LOGGER.warning(“Not sending email to user ” + assignee + “‘, user is not enrolled with identity service.”);
//            }

        }

    }

}
