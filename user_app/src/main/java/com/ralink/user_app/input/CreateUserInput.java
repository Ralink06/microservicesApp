package com.ralink.user_app.input;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserInput {

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;
}
