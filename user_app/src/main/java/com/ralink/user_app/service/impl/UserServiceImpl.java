package com.ralink.user_app.service.impl;

import com.ralink.user_app.factory.UserFactory;
import com.ralink.user_app.input.CreateUserInput;
import com.ralink.user_app.model.entity.user.User;
import com.ralink.user_app.output.CreateUserOutput;
import com.ralink.user_app.repository.UserRepository;
import com.ralink.user_app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserServiceImpl(final UserRepository userRepository, final UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public CreateUserOutput createUser(final CreateUserInput input) {
        final User user = userFactory.create(input);

        return new CreateUserOutput(userRepository.save(user).toSnapshot());
    }
}
