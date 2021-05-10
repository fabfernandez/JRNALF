package com.mercadolibre.finalchallengedemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    String SECRET = "SECRET";
    private final String PREFIX = "Bearer ";
    private final String SECRET_VALUE = System.getenv(SECRET);
    private ObjectMapper objectMapper = new ObjectMapper();




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            if (checkJWTToken(request, response)) {
                Claims claims = validateToken(request, response);
                DecodeToken.extractPayload(PREFIX,HEADER,SECRET_VALUE,request);
                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }

            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Map<String,String> status = new HashMap<>();
            status.put("error","Expired or Invalid Token");
            status.put("code", "403");
            status.put("status", "Forbidden");
            String json =objectMapper.writeValueAsString(status);
            PrintWriter printWriter = response.getWriter();
            printWriter.print(json);
            printWriter.flush();
            return;
        }
    }

    private Claims validateToken(HttpServletRequest request, HttpServletResponse response) {
        Claims claims;
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        try{
            claims = Jwts.parser().setSigningKey(SECRET_VALUE.getBytes()).parseClaimsJws(jwtToken).getBody();
            return  claims;
        }catch (SignatureException e){
            throw new MalformedJwtException("Invalid Token");
        }

    }

    /**
     * Authentication method in Spring flow
     *
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;
        return true;
    }

}