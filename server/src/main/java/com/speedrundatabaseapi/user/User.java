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
/**
 * Represents a user in the Speedrun Database API. This class implements the UserDetails
 * interface from Spring Security, providing user details for authentication and authorization.
 *
 * <p>The user entity includes information such as user ID, login, password, role, email, and
 * associations with game ratings, runs, and followers/following relationships.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see UserDetails
 */
@Entity
@Table
@JsonSerialize(using = UserCustomSerializer.class)
public class User implements UserDetails {

    /**
     * The unique identifier for the user.
     */
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

    /**
     * The login username of the user.
     */
    @Column(name = "login")
    private String login;

    /**
     * The password associated with the user's account.
     */
    @Column(name = "password")
    private String password;

    /**
     * The role of the user, represented as an enumeration.
     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * The email address associated with the user's account.
     */
    @Column(name = "email")
    private String email;

    /**
     * Set of game ratings associated with the user.
     */
    @OneToMany(mappedBy = "user")
    private Set<GameRating> ratings;

    /**
     * Set of runs associated with the user.
     */
    @OneToMany(mappedBy = "user")
    private Set<Run> userRuns;

    /**
     * Set of followers for the user.
     */
    @OneToMany(mappedBy = "follower")
    private Set<Follow> followedByUser;

    /**
     * Set of users being followed by the current user.
     */
    @OneToMany(mappedBy = "following")
    private Set<Follow> usersFollowingUser;

    /**
     * Default constructor for the User class.
     */
    public User() {
    }

    /**
     * Parameterized constructor for the User class.
     *
     * @param userId     The unique identifier for the user.
     * @param login      The login username of the user.
     * @param password   The password associated with the user's account.
     * @param role       The role of the user.
     * @param email      The email address associated with the user's account.
     * @param ratings    Set of game ratings associated with the user.
     * @param userRuns   Set of runs associated with the user.
     */
    public User(long userId, String login, String password, UserRole role, String email, Set<GameRating> ratings, Set<Run> userRuns) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.ratings = ratings;
        this.userRuns = userRuns;
    }

    /**
     * Getter for the user ID.
     *
     * @return The user ID.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Setter for the user ID.
     *
     * @param userId The new user ID.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Getter for the login username.
     *
     * @return The login username.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for the login username.
     *
     * @param login The new login username.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return login;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Getter for the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the user's password.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the user's role.
     *
     * @return The user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Setter for the user's role.
     *
     * @param role The new user role.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Getter for the user's email address.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the user's email address.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for the set of followers for the user.
     *
     * @return Set of followers for the user.
     */
    public Set<Follow> getFollowedByUser() {
        return followedByUser;
    }

    /**
     * Getter for the set of users being followed by the current user.
     *
     * @return Set of users being followed by the current user.
     */
    public Set<Follow> getUsersFollowingUser() {
        return usersFollowingUser;
    }
}

