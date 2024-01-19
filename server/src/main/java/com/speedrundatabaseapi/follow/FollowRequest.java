package com.speedrundatabaseapi.follow;

/**
 * Represents a request object for following or unfollowing a user.
 *
 * <p>The FollowRequest class encapsulates the information required for a user to follow or unfollow another user.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
public class FollowRequest {

    /**
     * The unique identifier of the user who wants to follow or unfollow another user.
     */
    private Long followerId;

    /**
     * The unique identifier of the user who is being followed or unfollowed.
     */
    private Long followingId;

    /**
     * Default constructor.
     */
    public FollowRequest() {
    }

    /**
     * Parameterized constructor to initialize the FollowRequest with specific values.
     *
     * @param followerId The unique identifier of the user who wants to follow or unfollow another user.
     * @param followingId The unique identifier of the user who is being followed or unfollowed.
     */
    public FollowRequest(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    /**
     * Get the follower's unique identifier.
     *
     * @return The unique identifier of the user who wants to follow or unfollow another user.
     */
    public Long getFollowerId() {
        return followerId;
    }

    /**
     * Set the follower's unique identifier.
     *
     * @param followerId The unique identifier of the user who wants to follow or unfollow another user.
     */
    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    /**
     * Get the following user's unique identifier.
     *
     * @return The unique identifier of the user who is being followed or unfollowed.
     */
    public Long getFollowingId() {
        return followingId;
    }

    /**
     * Set the following user's unique identifier.
     *
     * @param followingId The unique identifier of the user who is being followed or unfollowed.
     */
    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }
}
