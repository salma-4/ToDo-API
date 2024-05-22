package com.app.userservice.service.impl;

import com.app.userservice.entity.User;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY="mysecretkey";
    private final long ACCESS_TOKEN_VALIDTY  =60 *60*1000; // 1 hour
    private final JwtParser jwtParser;

    public JwtService()
    {
        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
    }
   public String generateToken(User user){

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis()+ACCESS_TOKEN_VALIDTY))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();

   }
   private final String AUTH_PREFIX="Bearer ";
   public String retrieveToken(HttpServletRequest req){
        String header = req.getHeader("Authorization");//Authorization
        if(header !=null  && header.startsWith(AUTH_PREFIX)){
            return header.substring(AUTH_PREFIX.length()); // 7
        }
        return null ;
   }
   public Claims retrieveClaims(String token){
       return jwtParser.parseClaimsJwt(token).getBody();
   }
    public Claims resolveClaims(HttpServletRequest req)
    {
        try {
            String token = retrieveToken(req);
            if (token != null) {
                return retrieveClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }
    public boolean isTokenExpired(Date expirationDate) throws AuthenticationException {
        try {
            if (expirationDate.before(new Date()))
                return true;
            else
                return false;
        } catch (Exception e) {
            throw e;
        }
    }

        public boolean isTokenValid(String token , UserDetails userDetails) {
        String username = userDetails.getUsername();
        Claims claims = retrieveClaims(token);
         // user in token is same with db ?
        return username.equals(claims.getSubject()) && !isTokenExpired(claims.getExpiration());
    }
    }

