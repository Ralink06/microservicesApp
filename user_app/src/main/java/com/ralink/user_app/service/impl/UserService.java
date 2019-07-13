package com.ralink.user_app.service.impl;

import com.ralink.user_app.input.CreateUserInput;
import com.ralink.user_app.model.snapshot.user.UserSnapshot;

public interface UserService {

    UserSnapshot createUser(CreateUserInput createUserInput);
}
