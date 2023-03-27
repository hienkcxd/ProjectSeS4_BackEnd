package com.example.BachEnd_Ses4.filter;

import com.example.BachEnd_Ses4.converter.ConverterToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@CrossOrigin("http://localhost:4200")
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private final ConverterToken converterToken = new ConverterToken();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh")){
         filterChain.doFilter(request, response);
     }else {
         String authorizationHeader = request.getHeader(AUTHORIZATION);
         if(authorizationHeader != null){
             try {
                 String username = converterToken.convertTokenToUserName(request);
                 log.info("CustomAuthorizationFilter line 47 - user decode:" + username);
                 Collection<SimpleGrantedAuthority> authorities = converterToken.convertTokenToRole(request);
                 log.info("CustomAuthorizationFilter line 49 - role user decode: "+ authorities);
                 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                 filterChain.doFilter(request, response);
             }catch (Exception exception){
                 log.error("Error logging in: {}", exception.getMessage());
                 response.setHeader("CustomAuthorizationFilter line 58 - error", exception.getMessage());
                 response.setStatus(FORBIDDEN.value());
                 Map<String, String> error = new HashMap<>();
                 error.put("error_message", exception.getMessage());
                 response.setContentType(APPLICATION_JSON_VALUE);
                 new ObjectMapper().writeValue(response.getOutputStream(), error);
             }
         }else {
             filterChain.doFilter(request, response);

         }
     }
    }
}
