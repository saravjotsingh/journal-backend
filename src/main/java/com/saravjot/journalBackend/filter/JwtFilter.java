package com.saravjot.journalBackend.filter;

import com.saravjot.journalBackend.service.UserDetailsServiceImp;
import com.saravjot.journalBackend.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try{

            // Extract JWT Token from the "Authorization" header
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                chain.doFilter(request, response);  // Continue filter chain if no token
                return;
            }

            String token = authHeader.substring(7); // Remove "Bearer " prefix
            String email = jwtUtil.extractEmail(token);
            String userId = jwtUtil.extractUserId(token);
            // Validate token and set authentication in context
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(email);

                if (jwtUtil.validateToken(token)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("email", email);
                    userMap.put("id", userId);
                    request.setAttribute("user", userMap);

                }
            }

            chain.doFilter(request, response); // Continue filter chain
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
