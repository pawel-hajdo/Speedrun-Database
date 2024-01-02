package com.speedrundatabaseapi.follow;

import com.speedrundatabaseapi.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "follow",
        uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "following_id"}))
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

    @Column(name = "follow_time")
    private LocalDateTime followTime;

    @PrePersist
    protected void onCreate() {
        followTime = LocalDateTime.now();
    }

    public Follow() {
    }

    public Follow(FollowKey id, User follower, User following) {
        this.id = id;
        this.follower = follower;
        this.following = following;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public LocalDateTime getFollowTime() {
        return followTime;
    }

    public void setFollowTime(LocalDateTime followTime) {
        this.followTime = followTime;
    }

    public FollowKey getId() {
        return id;
    }

    public void setId(FollowKey id) {
        this.id = id;
    }
}
