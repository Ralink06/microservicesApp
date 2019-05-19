package com.ralink.user_app.controller;


import com.ralink.user_app.finder.UserSnapshotFinder;
import com.ralink.user_app.input.CreateUserInput;
import com.ralink.user_app.model.snapshot.user.UserSnapshot;
import com.ralink.user_app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/user")
@RestController
public class UserController {

    private final UserSnapshotFinder userSnapshotFinder;
    private final UserService userService;

    public UserController(final UserSnapshotFinder userSnapshotFinder, final UserService userService) {
        this.userSnapshotFinder = userSnapshotFinder;
        this.userService = userService;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserSnapshot> findByUsername(@PathVariable("username") final String username) {
        final UserSnapshot userSnapshot = userSnapshotFinder.findByUsername(username)
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(userSnapshot);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserSnapshot> createUser(@RequestBody final CreateUserInput input) {

        return ResponseEntity.ok(userService.createUser(input));
    }
}

