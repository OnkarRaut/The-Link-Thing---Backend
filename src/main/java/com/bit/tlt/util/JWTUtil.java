package com.bit.tlt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JWTUtil {

    private static final Long EXPIRATION_TIME = 1000L * 60 * 60;

    public static String generateToken(String subject, String key, Pair<String, String> claims) {
        return Jwts.builder()
            .setSubject(subject)
            .addClaims(
                    Objects.isNull(claims)
                        ? Collections.emptyMap()
                        : Stream.of(claims).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond))
            )
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .signWith(SignatureAlgorithm.HS512, key)
            .compact();
    }

    public static Claims extractClaims(String token, String key) {
        return Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(Objects.requireNonNull(token))
            .getBody();
    }

}
