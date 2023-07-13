package com.example.demo.jwt;

import com.example.demo.domain.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtUtils {

//    private final String SECRET_KEY;
    private final Key key;

    private static final long EXPIRATION_TIME = 3600000;
    public static final String ID_CLAIM = "id";
    public static final String NAME_CLAIM = "name";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_HEADER_PREFIX = "bearer ";

    public JwtUtils(@Value("${jwt.secret-key}") String secret) {
        // key setting
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);

    }

    public String createToken(Long id, String name) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        // create payload
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID_CLAIM, id);
        claims.put(NAME_CLAIM, name);

        return Jwts.builder()
                .setIssuedAt(now)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(expiration)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("invalid signature of JWT");
        } catch (ExpiredJwtException e) {
            log.error("expired JWT token");
        } catch (UnsupportedJwtException e) {
            log.error("not supported JWT token");
        } catch (IllegalArgumentException e) {
            log.error("invalid JWT type");
        }
    }

    public String extractToken(String header) {
        return header.replace(AUTHORIZATION_HEADER_PREFIX, "").trim();
    }

    public Member getPayload(String token) {

        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new Member(((Number) claims.get(ID_CLAIM)).longValue(),
                (String) claims.get(NAME_CLAIM));
    }
}
