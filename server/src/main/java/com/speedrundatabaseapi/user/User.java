package com.speedrundatabaseapi.user;

import com.speedrundatabaseapi.rating.GameRating;
import jakarta.persistence.*;

import java.util.Set;
@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "user_id")
    private long userId;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    Set<GameRating> ratings;

    public User() {
    }

    public User(long userId, String login, String password, String role, String email) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
