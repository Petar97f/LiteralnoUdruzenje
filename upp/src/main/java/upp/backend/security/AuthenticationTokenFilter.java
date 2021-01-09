package upp.backend.security;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import upp.backend.service.UserDetailsServiceImpl;
import org.camunda.bpm.engine.identity.Group;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private IdentityService identityService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader("X-Auth-Token");
        String username = tokenUtils.getUsernameFromToken(authToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(tokenUtils.validateToken(authToken, userDetails)){
            	List<Group> groups = this.identityService.createGroupQuery().groupMember(username).list();
				List<String> userIds = groups.stream().map(Group::getId).collect(Collectors.toList());
            	Authentication auth = new Authentication(username, userIds);
				this.identityService.setAuthentication(auth);
				this.identityService.setAuthenticatedUserId(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        if (identityService.getCurrentAuthentication() == null) {
        	System.out.println("identtity service");
			List<String> userIds = new ArrayList<>();
			userIds.add("guests");
			Authentication auth = new Authentication("guest", userIds);
			this.identityService.setAuthenticatedUserId("guest");
			this.identityService.setAuthentication(auth);
		}
        
        chain.doFilter(request, response);
    }
}
