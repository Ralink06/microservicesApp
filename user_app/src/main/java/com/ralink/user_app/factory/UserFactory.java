package com.ralink.user_app.factory;

import com.ralink.user_app.input.CreateUserInput;
import com.ralink.user_app.model.entity.user.Role;
import com.ralink.user_app.model.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserFactory {

    public User create(final CreateUserInput input) {
        return User.builder()
                .email(input.getEmail())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .active(true)
                .roles(Collections.singleton(Role.USER))
                .build();
    }
}
