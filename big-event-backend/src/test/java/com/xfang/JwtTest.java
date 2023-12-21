package com.xfang;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
  @Test
  public void testGen() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", 1);
    claims.put("username", "admin");
    String token =
        JWT.create()
            .withClaim("claims", claims)
            .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
            .sign(Algorithm.HMAC256("xfang"));

    System.out.println(token);
  }

  @Test
  public void testParse() {
    String token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOjEsInVzZXJuYW1lIjoiYWRtaW4ifSwiZXhwIjoxNzAzMjM2ODY2fQ.dHf65PODiiSFcQI755iDgeJt-gpo2KQroruCQ7WbHd8";
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("xfang")).build();
    Map<String, Object> claims = jwtVerifier.verify(token).getClaim("claims").asMap();
    System.out.println(claims);
  }
}
