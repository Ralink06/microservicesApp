package com.ralink.gateway.service;

import com.ralink.gateway.client.UserClient;
import com.ralink.gateway.model.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class TokenAuthenticationService {


    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_NAME = "Authorization";

    private final long expirationTime;

    private final String secret;

    private final UserClient userClient;

    public TokenAuthenticationService(@Value("${security.jwt.expirationTime}") final long expirationTime,
                                      @Value("${security.jwt.secretKey}") final String secret,
                                      final UserClient userClient) {
        this.expirationTime = expirationTime;
        this.secret = secret;
        this.userClient = userClient;
    }

    public void addAuthenticationHeader(final HttpServletResponse res, final Authentication authentication) {

        String jwt = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        res.addHeader(HEADER_NAME, TOKEN_PREFIX + " " + jwt);
    }

    public Authentication getAuthentication(final HttpServletRequest request) {
        String token = request.getHeader(HEADER_NAME);
        if (token == null
                || token.isEmpty()
                || token.trim().isEmpty()) {
            return null;
        }
        String username = getUsername(token);

        if (username == null) {
            return null;
        }

        Optional<User> optional = userClient.retriveUserByUsername(username);

        if (!optional.isPresent()) {
            return null;
        }

        User userSnapshot = optional.get();

        return new UsernamePasswordAuthenticationToken(userSnapshot, null, userSnapshot.getAuthorities());
    }

    private String getUsername(final String token) {
        String username = null;

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            username = claims.getSubject();
        } catch (final ExpiredJwtException e) {
            log.info("Failed to parse JWT because of ExpiredJwtException <{}>", e.getMessage());
        } catch (final UnsupportedJwtException e) {
            log.info("Failed to parse JWT because of UnsupportedJwtException <{}>", e.getMessage());
        } catch (final MalformedJwtException e) {
            log.info("Failed to parse JWT because of MalformedJwtException <{}>", e.getMessage());
        } catch (final SignatureException e) {
            log.info("Failed to parse JWT because of SignatureException <{}>", e.getMessage());
        }

        return username;
    }

}
