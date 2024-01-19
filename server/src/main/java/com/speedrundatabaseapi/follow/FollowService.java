package com.speedrundatabaseapi.follow;

import com.speedrundatabaseapi.user.User;
import com.speedrundatabaseapi.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Service class for managing user follows.
 *
 * <p>This class provides methods to perform various operations related to user follows,
 * including retrieving all follows, following a user, retrieving users followed by a specific user,
 * and deleting a follow relationship between two users.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a FollowService with the specified repositories.
     *
     * @param followRepository The repository for Follow entities.
     * @param userRepository   The repository for User entities.
     */
    @Autowired
    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of all follows.
     *
     * @return A list of all follows.
     */
    public List<Follow> getAllFollows(){
        return followRepository.findAll();
    }

    /**
     * Follows a user.
     *
     * @param followerId  The ID of the user initiating the follow.
     * @param followingId The ID of the user being followed.
     * @throws EntityNotFoundException If either the follower or following user is not found.
     * @throws IllegalArgumentException If the follower is attempting to follow themselves.
     */
    public void followUser(Long followerId, Long followingId) {
        User user1 = userRepository.findById(followerId).orElseThrow(() -> new EntityNotFoundException("User with id" +followerId+ " not found"));
        User user2 = userRepository.findById(followingId).orElseThrow(() -> new EntityNotFoundException("User with id" +followingId+ " not found"));

        if(user1.getUserId() == user2.getUserId()){
            throw new IllegalArgumentException("You can`t follow yourself");
        }
        FollowKey followKey = new FollowKey(followerId, followingId);

        followRepository.save(new Follow(followKey, user1, user2));
    }

    /**
     * Retrieves the set of users followed by a specific user.
     *
     * @param followerId The ID of the user whose followed users are to be retrieved.
     * @return The set of users followed by the specified user.
     * @throws EntityNotFoundException If the user with the given ID is not found.
     */
    public Set<Follow> getUsersFollowedBy(Long followerId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + followerId + " not found"));
        return follower.getFollowedByUser();
    }

    /**
     * Deletes a follow relationship between two users.
     *
     * @param followerId  The ID of the user initiating the unfollow.
     * @param followingId The ID of the user being unfollowed.
     * @throws EntityNotFoundException If either the follower or following user is not found.
     */
    @Transactional
    public void deleteFollow(Long followerId, Long followingId) {
        userRepository.findById(followerId).orElseThrow(() -> new EntityNotFoundException("User with id " +followerId+ " not found"));
        userRepository.findById(followingId).orElseThrow(() -> new EntityNotFoundException("User with id " +followingId+ " not found"));

        FollowKey followKey = new FollowKey(followerId, followingId);
        followRepository.deleteById(followKey);
    }
}
