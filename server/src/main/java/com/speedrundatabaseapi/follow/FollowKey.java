package com.speedrundatabaseapi.follow;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite key class for the Follow entity.
 *
 * <p>The FollowKey class represents a composite key for the Follow entity.
 * It is used to uniquely identify a follow relationship between a follower and a following user.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Embeddable
public class FollowKey implements Serializable {

    /** The ID of the follower user. */
    @Column(name = "follower_id")
    Long followerId;

    /** The ID of the following user. */
    @Column(name = "following_id")
    Long followingId;

    /**
     * Default constructor for FollowKey.
     */
    public FollowKey() {
    }

    /**
     * Parameterized constructor for FollowKey.
     *
     * @param followerId  The ID of the follower user.
     * @param followingId The ID of the following user.
     */
    public FollowKey(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    /**
     * Get the ID of the follower user.
     *
     * @return The ID of the follower user.
     */
    public Long getFollowerId() {
        return followerId;
    }

    /**
     * Set the ID of the follower user.
     *
     * @param followerId The ID of the follower user.
     */
    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    /**
     * Get the ID of the following user.
     *
     * @return The ID of the following user.
     */
    public Long getFollowingId() {
        return followingId;
    }

    /**
     * Set the ID of the following user.
     *
     * @param followingId The ID of the following user.
     */
    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }
}
