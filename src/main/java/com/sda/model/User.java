package com.sda.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Username cannot be empty")
    @Email(message = "Email format is invalid")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @OneToOne(cascade = CascadeType.MERGE)
    private Role role;

}
