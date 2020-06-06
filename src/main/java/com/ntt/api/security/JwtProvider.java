package com.ntt.api.security;

import com.ntt.entity.MyUserDetails;
import com.ntt.repository.AccountRepository;
import com.ntt.service.MyUserDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtProvider {
    static final long  EXPIRATIONTIME = 86400000;
    static final String SECRET = "sunsecret";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    public String generateJwt(MyUserDetails myUserDetails){
        long expirationTime = EXPIRATIONTIME;
        return Jwts.builder()
                .setId(myUserDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getId();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException  ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }
}
