package com.ot.BoboLike.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "please enter name")
    private String name;

    @Column(nullable = false, unique = true, length = 10)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Please Enter the password")
    private String password;

    private String address;

    private String role;

    private String otp;

}