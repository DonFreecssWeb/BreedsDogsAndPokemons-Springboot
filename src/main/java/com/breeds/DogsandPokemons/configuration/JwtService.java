package com.breeds.DogsandPokemons.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String  SECRET_KEY ="28472B4B6250655368566D5970337336763979244226452948404D635166546A";

    private final byte []  keyByte;
    private final long timeExpirationInSeconds = 60;
    JwtService(){
        keyByte = Decoders.BASE64.decode(SECRET_KEY);
    }
    public boolean validateToken(String token){
        //si el token no expiró
        return !isTokenExpired(token);
    }

    private String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey(){
        byte[] secretKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretKey);
    }

    public String generateToken(){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + timeExpirationInSeconds * 1000))
                .signWith(Keys.hmacShaKeyFor(keyByte))
                .compact();
    }


}
