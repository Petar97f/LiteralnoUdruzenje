package upp.backend.security;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import upp.backend.model.User;
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
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        System.out.println("request"+ request);
        String authToken = httpRequest.getHeader("X-Auth-Token");
        
       
        System.out.println("authToken"+ authToken);
     
        if (authToken != null) {
        	 httpResponse.setHeader("Access-Control-Allow-Origin", "*");
             httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
             httpResponse.setHeader("Access-Control-Max-Age", "3600");
             httpResponse.setHeader("Access-Control-Allow-Headers",
     				"Origin, X-Requested-With, Content-Type, Accept, x-requested-with, authorization,  X-Auth-Token");
            String username = tokenUtils.getUsernameFromToken(authToken);
            System.out.println("user"+ username);
	        if (username != null /*&& SecurityContextHolder.getContext().getAuthentication() == null*/){
	            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	            System.out.println("user"+ username);
	            if(tokenUtils.validateToken(authToken, userDetails)) {
	            	 System.out.println("validateToken"+ username);
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
        }
        if (identityService.getCurrentAuthentication() == null) {
        	System.out.println("identtity service");
			List<String> userIds = new ArrayList<>();
			userIds.add("guests");
			Authentication auth = new Authentication("guest", userIds);
			this.identityService.setAuthenticatedUserId("guest");
			this.identityService.setAuthentication(auth);
		}
    	System.out.println("end");
        chain.doFilter(request, response);
    }
}
