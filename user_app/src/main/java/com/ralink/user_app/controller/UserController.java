package com.ralink.user_app.controller;


import com.ralink.user_app.finder.UserSnapshotFinder;
import com.ralink.user_app.input.CreateUserInput;
import com.ralink.user_app.model.snapshot.user.UserSnapshot;
import com.ralink.user_app.output.CreateUserOutput;
import com.ralink.user_app.service.impl.UserServiceImpl;
import com.ralink.user_app.validator.CreateUserInputValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/user")
@RestController
@Slf4j
public class UserController {

    private final UserSnapshotFinder userSnapshotFinder;
    private final UserServiceImpl userServiceImpl;
    private final Validator createUserInputValidator;

    public UserController(final UserSnapshotFinder userSnapshotFinder,
                          final UserServiceImpl userServiceImpl,
                          final CreateUserInputValidator createUserInputValidator) {
        this.userSnapshotFinder = userSnapshotFinder;
        this.userServiceImpl = userServiceImpl;
        this.createUserInputValidator = createUserInputValidator;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserSnapshot> findByUsername(@PathVariable("username") final String username) {
        final UserSnapshot userSnapshot = userSnapshotFinder.findByUsername(username)
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(userSnapshot);
    }

    @InitBinder("createUserInput")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(createUserInputValidator);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<CreateUserOutput> createUser(@RequestBody @Valid final CreateUserInput createUserInput) {
        return ResponseEntity.ok(userServiceImpl.createUser(createUserInput));
    }
}

