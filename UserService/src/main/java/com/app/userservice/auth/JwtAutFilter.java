package com.app.userservice.auth;

import com.app.userservice.repository.UserRepository;
import com.app.userservice.service.impl.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// TODO logging
// first to meet
@Component
@RequiredArgsConstructor
public class JwtAutFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");

            if (header == null || !header.startsWith("Bearer ")) { // no header or invalid
                filterChain.doFilter(request, response);
                System.out.println("yalahwaaay");
                return;
            }

            String token = jwtService.retrieveToken(request);
            Claims claims = jwtService.resolveClaims(request);
            String email = claims.getSubject();

            // check if user is in db                                 // user already authenticated ==> from validate Jwt
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {  //first time to log in
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);// check in db
                // found user ==> validate him (jwtService ? valid)
                if (userDetails != null && jwtService.isTokenValid(token, userDetails)) {
                    // update ContextHolder
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        filterChain.doFilter(request, response);
    }
}
