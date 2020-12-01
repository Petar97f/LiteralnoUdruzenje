package com.sep.tim26;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sep.tim26.security.JwtAuthenticationRequest;
import com.sep.tim26.security.TokenUtils;

import feign.FeignException;

@Component
public class AuthFilter extends ZuulFilter{

	@Autowired
	private AuthClient authClient;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Override
	public String filterType() {
		return "pre";
	}
	
	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		/*String authRequest = request.getHeader("Authorization");
		System.out.println(authRequest);*/
		
		
		try {
			//System.out.println(jwt.getUsername());
			//System.out.println(jwt.getPassword());
			//authClient.login(jwt);
			//ctx.addZuulRequestHeader("username", username);
			//ctx.addZuulRequestHeader("password", password);
			//ctx.addZuulRequestHeader("Authorization", "jwt");
		} catch (FeignException.NotFound e) {
			setFailedRequest("User does not exist", 403);
		}
		
		return null;
	}
	
    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }
}
