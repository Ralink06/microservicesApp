package com.ralink.gateway.config.jwt;

import com.ralink.gateway.model.AccountCredentials;
import com.ralink.gateway.model.User;
import com.ralink.gateway.service.TokenAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenAuthenticationService tokenAuthenticationService;

    public JwtAuthenticationFilter(final TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest req,
                                                final HttpServletResponse res) {
        AccountCredentials creds = null;
        try {
            creds = new ObjectMapper()
                    .readValue(req.getInputStream(), AccountCredentials.class);
        } catch (IOException e) {
            //TODO improve exception handle
            throw new RuntimeException();
        }


        log.info("Login attempt <{}>", creds.getUsername());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), Collections.emptyList());
        return getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        tokenAuthenticationService.addAuthenticationHeader(res, auth);

        log.info("Authentication successful <{}>", ((User) auth.getPrincipal()).getUsername());
    }
}