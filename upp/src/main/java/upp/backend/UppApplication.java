package upp.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.util.FileSystemUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import upp.backend.model.Genre;
import upp.backend.model.UserRole;

import org.camunda.bpm.engine.identity.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebMvc
@EnableFeignClients
@SpringBootApplication
public class UppApplication {

	@Autowired
	private IdentityService identityService;
	
	public static void main(String[] args) {
		createDirectory();
		SpringApplication.run(UppApplication.class, args);
	}

	private static void createDirectory() {
		try {
			if (Files.exists(Paths.get("uploads"))) {
				FileSystemUtils.deleteRecursively(Paths.get("uploads").toFile());
			}
			Files.createDirectory(Paths.get("uploads"));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source =
				new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean bean =
				new FilterRegistrationBean(new CorsFilter(source));

		bean.setOrder(0);
		return bean;
	}
	
	@PostConstruct
	private void createUserGroup() {
		List<Group> groups = identityService.createGroupQuery()
				.groupIdIn("users", "guests", "members", "adminMember").list();
		if (groups.isEmpty()) {

			Group usersGroup = identityService.newGroup("users");
			usersGroup.setName("users");
			identityService.saveGroup(usersGroup);
			
			Group guestsGroup = identityService.newGroup("guests");
			guestsGroup.setName("guests");
			identityService.saveGroup(guestsGroup);
			
			Group membersGroup = identityService.newGroup("members");
			membersGroup.setName("members");
			identityService.saveGroup(membersGroup);

			Group editorsGroup = identityService.newGroup("editors");
			membersGroup.setName("editors");
			identityService.saveGroup(editorsGroup);
			
			Group adminMembersGroup = identityService.newGroup("adminMember");
			adminMembersGroup.setName("adminMember");
			identityService.saveGroup(adminMembersGroup);

			Group betaReadersMembersGroup = identityService.newGroup("betaReaders");
			betaReadersMembersGroup.setName("betaReaders");
			identityService.saveGroup(betaReadersMembersGroup);
		}
		List<User> users = identityService.createUserQuery().userIdIn("user6", "userMember7", "userMember8",
				"userMember9", "admin7").list();
		if (users.isEmpty()) {
			upp.backend.model.User user5 = new upp.backend.model.User();
			user5.setUsername("user5");
			user5.setName("User5");
			user5.setSurname("Prezime5");
			user5.setEmail("user5@gmail.com");
			user5.setPassword("$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6");
			user5.setCity("Grad5");
			user5.setCountry("Country5");
			saveInCamunda(user5);
			identityService.createMembership("user5", "editors");


			upp.backend.model.User user6 = new upp.backend.model.User();
			user6.setUsername("user6");
			user6.setName("User6");
			user6.setSurname("Prezime6");
			user6.setEmail("user6@gmail.com");
			user6.setPassword("$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6");
			user6.setCity("Grad6");
			user6.setCountry("Country2");
			saveInCamunda(user6);
			identityService.createMembership("user6", "members");
			
			upp.backend.model.User user7 = new upp.backend.model.User();
			user7.setUsername("userMember7");
			user7.setName("UserMember7");
			user7.setSurname("PrezimeMember7");
			user7.setEmail("user7@gmail.com");
			user7.setPassword("$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6");
			user7.setCity("Grad6");
			user7.setCountry("Country2");
			saveInCamunda(user7);
			identityService.createMembership("userMember7", "members");
			
			upp.backend.model.User user8 = new upp.backend.model.User();
			user8.setUsername("userMember8");
			user8.setName("UserMember8");
			user8.setSurname("PrezimeMember8");
			user8.setEmail("user8@gmail.com");
			user8.setPassword("$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6");
			user8.setCity("Grad6");
			user8.setCountry("Country2");
			saveInCamunda(user8);
			identityService.createMembership("userMember8", "members");

			upp.backend.model.User user9 = new upp.backend.model.User();
			user9.setUsername("userMember9");
			user9.setName("UserMember9");
			user9.setSurname("PrezimeMember9");
			user9.setEmail("user9@gmail.com");
			user9.setPassword("$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6");
			user9.setCity("Grad6");
			user9.setCountry("Country2");
			saveInCamunda(user9);
			identityService.createMembership("userMember9", "members");

			upp.backend.model.User reader1 = new upp.backend.model.User();
			reader1.setUsername("betareader1");
			reader1.setName("UserMember9");
			reader1.setSurname("PrezimeMember9");
			reader1.setEmail("betareader1@gmail.com");
			reader1.setPassword("$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6");
			reader1.setCity("Grad6");
			reader1.setCountry("Country2");
			saveInCamunda(reader1);
			identityService.createMembership("betareader1", "betaReaders");


			upp.backend.model.User reader2 = new upp.backend.model.User();
			reader2.setUsername("betareader2");
			reader2.setName("UserReader2");
			reader2.setSurname("PrezimeReader2");
			reader2.setEmail("betareader2@gmail.com");
			reader2.setPassword("$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6");
			reader2.setCity("Grad6");
			reader2.setCountry("Country2");
			saveInCamunda(reader2);
			identityService.createMembership("betareader2", "betaReaders");
			
			upp.backend.model.User admin7 = new upp.backend.model.User();
			admin7.setUsername("admin7");
			admin7.setName("Admin7");
			admin7.setSurname("Prezime7");
			admin7.setEmail("admin7@gmail.com");
			admin7.setPassword("$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6");
			admin7.setCity("Grad6");
			admin7.setCountry("Country2");
			saveInCamunda(admin7);
			identityService.createMembership("admin7", "adminMember");
		}
		
	}
	
	private void saveInCamunda(upp.backend.model.User newUser) {
		try {
			User user = identityService.newUser(newUser.getUsername());
		    user.setFirstName(newUser.getName());
		    user.setLastName(newUser.getSurname());
		    user.setEmail(newUser.getEmail());
		    user.setPassword(newUser.getPassword());
		    identityService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
