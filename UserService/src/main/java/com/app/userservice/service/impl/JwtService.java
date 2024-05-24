package com.app.userservice.service.impl;

import com.app.userservice.entity.User;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY = "mysecretkey";
    private final long ACCESS_TOKEN_VALIDITY = 60 * 60 * 1000; // 1 hour
    private final JwtParser jwtParser;

    public JwtService() {
        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private final String AUTH_PREFIX = "Bearer ";

    public String retrieveToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization"); // Get the Authorization header
        if (header != null && header.startsWith(AUTH_PREFIX)) {
            // Extract the token from the header and return it
            return header.substring(AUTH_PREFIX.length());
        }
        return null;
    }

    public Claims retrieveClaims(String token) {
        try {
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (JwtException e) {
            e.printStackTrace();
            throw new JwtException("Error parsing JWT claims: " + e.getMessage());
        }
    }

    public String extractUsername(String token) {
        return retrieveClaims(token).getSubject();
    }

    public Claims resolveClaims(HttpServletRequest req) {
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

    public boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = userDetails.getUsername();
        Claims claims = retrieveClaims(token);
        return username.equals(claims.getSubject()) && !isTokenExpired(claims.getExpiration());
    }

    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
