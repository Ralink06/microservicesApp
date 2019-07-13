package com.ralink.user_app.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserInput {

    private final String email;
    private final String firstName;
    private final String lastName;
    private final String password;
}
