package com.ralink.user_app.validator;

import com.ralink.user_app.finder.UserSnapshotFinder;
import com.ralink.user_app.input.CreateUserInput;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreateUserInputValidator implements Validator {

    private UserSnapshotFinder userSnapshotFinder;

    public CreateUserInputValidator(UserSnapshotFinder userSnapshotFinder) {
        this.userSnapshotFinder = userSnapshotFinder;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateUserInput.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateUserInput input = (CreateUserInput) target;

        userSnapshotFinder.findByUsername(input.getEmail())
                .ifPresent(userSnapshot -> errors.rejectValue("email", "This email is already used"));
    }
}
