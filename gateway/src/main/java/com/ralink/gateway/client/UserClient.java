package com.ralink.gateway.client;

import com.ralink.gateway.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<User> retriveUserByUsername(final String username) {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8081/user/" + username, User.class));
    }
}
