package com.speedrundatabaseapi.follow;

public class FollowRequest {
    private Long followerId;
    private Long followingId;

    public FollowRequest() {
    }

    public FollowRequest(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }
}
