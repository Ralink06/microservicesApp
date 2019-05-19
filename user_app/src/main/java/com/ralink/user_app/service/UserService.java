package com.ralink.user_app.service;

import com.ralink.user_app.factory.UserFactory;
import com.ralink.user_app.input.CreateUserInput;
import com.ralink.user_app.model.entity.user.User;
import com.ralink.user_app.model.snapshot.user.UserSnapshot;
import com.ralink.user_app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserService(final UserRepository userRepository, final UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public UserSnapshot createUser(CreateUserInput input) {
        final User user = userFactory.create(input);

        return userRepository.save(user)
                .toSnapshot();
    }
}
