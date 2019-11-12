package com.ralink.user_app.service;

import com.ralink.user_app.input.CreateUserInput;
import com.ralink.user_app.model.snapshot.user.UserSnapshot;
import com.ralink.user_app.output.CreateUserOutput;

public interface UserService {

    CreateUserOutput createUser(CreateUserInput createUserInput);
}
