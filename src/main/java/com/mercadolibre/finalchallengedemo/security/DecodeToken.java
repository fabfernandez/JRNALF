package com.mercadolibre.finalchallengedemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;

public class DecodeToken {

     public static Integer location = 0;
    public static Integer extractPayload(String prefix, String header, String secret, HttpServletRequest request) {
        String jwtToken = request.getHeader(header).replace(prefix, "");
        Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtToken).getBody();
        location = Integer.parseInt(claims.get("authorities").toString().replace("[","").replace("]",""));
        return location;
    }
}