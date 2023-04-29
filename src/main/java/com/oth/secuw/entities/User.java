package com.oth.secuw.entities;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.*;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private Role role;

    public Long getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Argon2 argon2 = Argon2Factory.create();
        String hashedPassword = argon2.hash(10, 65536, 1, password);
        this.password = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public enum Role{
    ADMIN, USER
    }
}
