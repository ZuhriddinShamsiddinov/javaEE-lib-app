package com.example.libapp.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private User.Role role = Role.USER;

    public enum Role {
        USER, ADMIN
    }
}
