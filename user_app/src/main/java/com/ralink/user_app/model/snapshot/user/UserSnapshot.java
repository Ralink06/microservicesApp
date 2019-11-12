package com.ralink.user_app.model.snapshot.user;

import com.ralink.user_app.model.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class UserSnapshot {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final boolean active;
    private final String email;
    private final Set<Role> roles;
}
