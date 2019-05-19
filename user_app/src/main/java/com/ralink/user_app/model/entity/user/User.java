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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 60)
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 60)
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

