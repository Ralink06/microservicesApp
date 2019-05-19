package com.ralink.gateway.service;

import com.ralink.gateway.client.UserClient;
import com.ralink.gateway.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userServiceCustomImplementation")
public class UserService implements UserDetailsService {

    private final UserClient userClient;

    public UserService(final UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userClient.retriveUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}
