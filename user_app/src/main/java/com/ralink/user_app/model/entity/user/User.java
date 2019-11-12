package com.ralink.user_app.model.entity.user;

import com.ralink.user_app.model.snapshot.user.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"")
public class User {

    public static final int NAME_MAX_LENGTH = 60;
    public static final int NAME_MIN_LENGTH = 2;
    public static final int EMAIL_MAX_LENGTH = 255;
    public static final int PASSWORD_MAX_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private boolean active;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    @Size(max = EMAIL_MAX_LENGTH)
    private String email;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    public UserSnapshot toSnapshot() {
        return UserSnapshot.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .password(this.password)
                .active(this.active)
                .email(this.email)
                .roles(this.roles)
                .build();
    }
}

