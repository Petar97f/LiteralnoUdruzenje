package com.sep.tim26.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sep.tim26.AuthClient;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {

    @Value("Xws")
    private String APP_NAME;

    @Value("myXAuthSecret")
    private String secret;

    @Value("18000")
    private int EXPIRES_IN;

    @Value("Authorization")
    private String AUTH_HEADER;
    @Autowired
    private AuthClient authClient;
    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    @Autowired
    private TimeProvider timeProvider;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateToken(String email, String role, Long id) {
        return Jwts
                .builder()
                .setIssuer(APP_NAME)
                .setSubject(email)
                .setAudience(generateAudience())
                .setIssuedAt(timeProvider.now())
                .setExpiration(generateExpirationDate())
                .claim("role", role) //postavljanje proizvoljnih podataka u telo JWT tokena
                .claim("id", id.toString())
                .claim("created", new Date(System.currentTimeMillis()))
                .claim("user", authClient.getUser(id))
                .claim("sub", authClient.getUsername(id))
                .signWith(SIGNATURE_ALGORITHM, secret).compact();
    }

    private String generateAudience() {
        return AUDIENCE_WEB;
    }

    private Date generateExpirationDate() {
        return new Date(timeProvider.now().getTime() + EXPIRES_IN);
    }

    
    
    
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(timeProvider.now());
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, secret).compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getIssuedAtDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
                && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
    }

    // Funkcija za validaciju JWT tokena
 /*   public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);

        return (username != null && username.equals(userDetails.getUsername()));
    }*/

    public String getUsernameFromToken(String token) {
        String username;
        System.out.println("getUsernameFromToken");
        try {
            Claims claims = this.getClaimsFromToken(token);
            System.out.println(claims);
            username = claims.getSubject();
        } catch (Exception ex) {
            username = null;
        }
        return username;

    }
    
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            claims = null;
        }
        return claims;
    }

    /**
     * Funkcija koja vraca rolu iz tokena
     * @author Nikola*/
    public String getRoleFromToken(String token) {
        String role;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            role = (String)claims.get("role");
        } catch (Exception e) {
            role = null;
        }
        return role;
    }
    
    public String getIdFromToken(String token) {
    	String id;
    	try {
    		final Claims claims = this.getAllClaimsFromToken(token);
    		id = (String) claims.get("id");
    	} catch (Exception e) {
    		id = null;
    	}
    	return id;
    }
    
    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public int getExpiredIn() {
        return EXPIRES_IN;
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);
        System.out.println("Utils:  |"+authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	System.out.println("OVDE?");
            return authHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(timeProvider.now());
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = this.getAudienceFromToken(token);
        return (audience.equals(AUDIENCE_TABLET) || audience.equals(AUDIENCE_MOBILE));
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        
        return claims;
    }
}
