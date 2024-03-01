package com.backend.integration.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.backend.integration.Repo.UserRepo;
import com.backend.integration.Service.TokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



// Component responsible for filtering incoming requests and handling JWT authentication
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenProvider tokenService; // Autowired TokenProvider for token operations

    @Autowired
    UserRepo userRepo; // Autowired UserRepo for user data retrieval

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            var token = this.recoverToken(request); // Attempt to retrieve the JWT token from the request
            if (token != null) {
                var userName = tokenService.validateToken(token); // Validate the token and get the associated username
                var user = userRepo.findByUserName(userName); // Retrieve user details from the database using the username
                if (user != null) {
                    // If user is found, create an authentication token and set it in the security context
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    // Log successful authentication
                    System.out.println("Authentication successful for user: " + userName);
                } else {
                    // Log user not found
                    System.out.println("User not found for token: " + token);
                }
            }
            filterChain.doFilter(request, response); // Continue with the filter chain
        } catch (JWTVerificationException e) {
            // Token is invalid or expired
            // Log the exception and return unauthorized response
            System.out.println("JWT Verification failed: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
        }
    }

    // Recover JWT token from the Authorization header
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
