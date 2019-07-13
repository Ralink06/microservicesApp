package com.ralink.user_app.service.impl;

import com.ralink.user_app.factory.UserFactory;
import com.ralink.user_app.input.CreateUserInput;
import com.ralink.user_app.model.entity.user.User;
import com.ralink.user_app.model.snapshot.user.UserSnapshot;
import com.ralink.user_app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserServiceImpl(final UserRepository userRepository, final UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public UserSnapshot createUser(final CreateUserInput input) {
        final User user = userFactory.create(input);

        return userRepository.save(user)
                .toSnapshot();
    }
}
