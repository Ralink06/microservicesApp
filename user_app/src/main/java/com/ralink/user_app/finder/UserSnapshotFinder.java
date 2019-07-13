package com.ralink.user_app.finder;

import com.ralink.user_app.model.snapshot.user.UserSnapshot;
import com.ralink.user_app.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserSnapshotFinder {

    private final UserRepository userRepository;

    public UserSnapshotFinder(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserSnapshot> findByUsername(final String username) {
        return Optional.ofNullable(userRepository.findByEmail(username).toSnapshot());
    }
}
