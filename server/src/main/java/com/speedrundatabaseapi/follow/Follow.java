package com.speedrundatabaseapi.follow;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.speedrundatabaseapi.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class representing the relationship between users for following.
 *
 * <p>The Follow class is used to store information about users who follow other users.
 * It includes details such as follower, following, and the time when the follow occurred.</p>
 *
 * <p>The class is annotated with JPA annotations for mapping to the database table.
 * The unique constraint ensures that a user can only follow another user once.</p>
 *
 * <p>The follow time is automatically set to the current date and time before persisting
 * the entity in the database.</p>
 *
 * <p>The class is also annotated with a custom JSON serializer for handling JSON serialization.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Entity
@Table(name = "follow",
        uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "following_id"}))
@JsonSerialize(using = FollowCustomSerializer.class)
public class Follow {

    @EmbeddedId
    private FollowKey id;

    @ManyToOne
    @MapsId("followerId")
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne
    @MapsId("followingId")
    @JoinColumn(name = "following_id", nullable = false)
    private User following;

    @Column(name = "follow_time", nullable = false)
    private LocalDateTime followTime;

    /**
     * Lifecycle callback method called before persisting the entity.
     * Sets the follow time to the current date and time.
     */
    @PrePersist
    protected void onCreate() {
        followTime = LocalDateTime.now();
    }

    /**
     * Default constructor for the Follow class.
     */
    public Follow() {
    }

    /**
     * Parameterized constructor for the Follow class.
     *
     * @param id        The composite key for the Follow entity.
     * @param follower  The user who is following.
     * @param following The user who is being followed.
     */
    public Follow(FollowKey id, User follower, User following) {
        this.id = id;
        this.follower = follower;
        this.following = following;
    }

    /**
     * Getter method for retrieving the follower user.
     *
     * @return The follower user.
     */
    public User getFollower() {
        return follower;
    }

    /**
     * Setter method for setting the follower user.
     *
     * @param follower The follower user.
     */
    public void setFollower(User follower) {
        this.follower = follower;
    }

    /**
     * Getter method for retrieving the following user.
     *
     * @return The following user.
     */
    public User getFollowing() {
        return following;
    }

    /**
     * Setter method for setting the following user.
     *
     * @param following The following user.
     */
    public void setFollowing(User following) {
        this.following = following;
    }

    /**
     * Getter method for retrieving the follow time.
     *
     * @return The time when the follow occurred.
     */
    public LocalDateTime getFollowTime() {
        return followTime;
    }

    /**
     * Setter method for setting the follow time.
     *
     * @param followTime The time when the follow occurred.
     */
    public void setFollowTime(LocalDateTime followTime) {
        this.followTime = followTime;
    }

    /**
     * Getter method for retrieving the composite key of the Follow entity.
     *
     * @return The composite key of the Follow entity.
     */
    public FollowKey getId() {
        return id;
    }

    /**
     * Setter method for setting the composite key of the Follow entity.
     *
     * @param id The composite key of the Follow entity.
     */
    public void setId(FollowKey id) {
        this.id = id;
    }
}
