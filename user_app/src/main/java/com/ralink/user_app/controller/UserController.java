package com.ralink.user_app.controller;


import com.ralink.user_app.finder.UserSnapshotFinder;
import com.ralink.user_app.input.CreateUserInput;
import com.ralink.user_app.model.snapshot.user.UserSnapshot;
import com.ralink.user_app.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/user")
@RestController
@Slf4j
public class UserController {

    private final String portNumber;

    private final UserSnapshotFinder userSnapshotFinder;
    private final UserServiceImpl userServiceImpl;

    public UserController(@Value("${server.port}")final String portNumber, final UserSnapshotFinder userSnapshotFinder, final UserServiceImpl userServiceImpl) {
        this.portNumber = portNumber;
        this.userSnapshotFinder = userSnapshotFinder;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserSnapshot> findByUsername(@PathVariable("username") final String username) {
        final UserSnapshot userSnapshot = userSnapshotFinder.findByUsername(username)
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(userSnapshot);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserSnapshot> createUser(@RequestBody final CreateUserInput input) {
        log.info("Port number {}", portNumber);

        return ResponseEntity.ok(userServiceImpl.createUser(input));
    }
}

