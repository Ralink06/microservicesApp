package com.ralink.user_app.input;

import com.ralink.user_app.model.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateUserInput {

    @NotNull
    @Email
    @Size(max = User.EMAIL_MAX_LENGTH)
    private String email;

    @NotBlank
    @Size(min = User.NAME_MIN_LENGTH, max = User.NAME_MAX_LENGTH)
    private String firstName;

    @NotBlank
    @Size(min = User.NAME_MIN_LENGTH, max = User.NAME_MAX_LENGTH)
    private String lastName;

    @NotBlank
    @Size(max = User.PASSWORD_MAX_LENGTH)
    private String password;
}
