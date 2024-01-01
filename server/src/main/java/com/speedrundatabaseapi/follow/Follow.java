package com.speedrundatabaseapi.follow;

import com.speedrundatabaseapi.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "follow",
        uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "following_id"}))
public class Follow {

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    @Id
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    @Id
    private User following;

    @Column(name = "follow_time")
    private LocalDateTime followTime;

    @PrePersist
    protected void onCreate() {
        followTime = LocalDateTime.now();
    }

    public Follow() {
    }

    public Follow(User follower, User following, LocalDateTime followTime) {
        this.follower = follower;
        this.following = following;
        this.followTime = followTime;
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
}
