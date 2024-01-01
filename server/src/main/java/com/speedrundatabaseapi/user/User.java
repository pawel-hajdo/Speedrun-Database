package com.speedrundatabaseapi.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.speedrundatabaseapi.follow.Follow;
import com.speedrundatabaseapi.rating.GameRating;
import com.speedrundatabaseapi.run.Run;
import jakarta.persistence.*;

import java.util.Set;
@Entity
@Table
@JsonSerialize(using = UserCustomSerializer.class)
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
    private UserRole role;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<GameRating> ratings;

    @OneToMany(mappedBy = "user")
    private Set<Run> userRuns;

    @OneToMany(mappedBy = "follower")
    private Set<Follow> followers;

    @OneToMany(mappedBy = "following")
    private Set<Follow> following;

    public User() {
    }

    public User(long userId, String login, String password, UserRole role, String email, Set<GameRating> ratings, Set<Run> userRuns) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.ratings = ratings;
        this.userRuns = userRuns;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
