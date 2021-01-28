package upp.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

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
		SpringApplication.run(UppApplication.class, args);
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
				.groupIdIn("users", "guests").list();
		if (groups.isEmpty()) {

			Group usersGroup = identityService.newGroup("users");
			usersGroup.setName("users");
			identityService.saveGroup(usersGroup);
			
			Group guestsGroup = identityService.newGroup("guests");
			guestsGroup.setName("guests");
			identityService.saveGroup(guestsGroup);
		}
	}
}
