package com.ralink.user_app.output;

import com.ralink.user_app.model.entity.user.Role;
import com.ralink.user_app.model.snapshot.user.UserSnapshot;

import java.util.Set;

public class CreateUserOutput {

    private String firstName;
    private String lastName;
    private boolean active;
    private String email;
    private Set<Role> roles;

    public CreateUserOutput(final UserSnapshot user) {
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.active = user.isActive();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
