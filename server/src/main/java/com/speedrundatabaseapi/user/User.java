package com.speedrundatabaseapi.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.speedrundatabaseapi.follow.Follow;
import com.speedrundatabaseapi.rating.GameRating;
import com.speedrundatabaseapi.run.Run;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
@Entity
@Table
@JsonSerialize(using = UserCustomSerializer.class)
public class User implements UserDetails {

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
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<GameRating> ratings;

    @OneToMany(mappedBy = "user")
    private Set<Run> userRuns;

    @OneToMany(mappedBy = "follower")
    private Set<Follow> followedByUser;

    @OneToMany(mappedBy = "following")
    private Set<Follow> usersFollowingUser;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public Set<Follow> getFollowedByUser() {
        return followedByUser;
    }

    public Set<Follow> getUsersFollowingUser() {
        return usersFollowingUser;
    }
}
