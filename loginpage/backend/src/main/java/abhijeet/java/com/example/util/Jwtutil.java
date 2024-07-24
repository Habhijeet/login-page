package abhijeet.java.com.example.util;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.example.auth.util.Jwts;
import com.example.auth.util.SignatureAlgorithm;

@Component
public class Jwtutil {

         private String secret = "your_secret_key";

    public String generateToken(String username) {
        return ((Object) Jwts.builder())
                .setSubject(username)
                .setIssuedAt(new Date(0))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractUsername(String token) {
        return ((Object) Jwts.parser())
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return ((Object) Jwts.parser())
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date(0));
    }
}
