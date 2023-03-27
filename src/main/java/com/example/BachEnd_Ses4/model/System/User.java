package com.example.BachEnd_Ses4.model.System;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long deviceActives;
    private String name;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phone;
    private String password;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public User(Long id, String name, String userName,String email, String phone, String password, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.email = email;
        this.phone = phone;
    }

}
