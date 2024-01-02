package com.speedrundatabaseapi.follow;

import com.speedrundatabaseapi.user.User;
import com.speedrundatabaseapi.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    @Autowired
    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public List<Follow> getAllFollows(){
        return followRepository.findAll();
    }

    public void followUser(Long followerId, Long followingId) {
        User user1 = userRepository.findById(followerId).orElseThrow(() -> new EntityNotFoundException("User with id" +followerId+ " not found"));
        User user2 = userRepository.findById(followingId).orElseThrow(() -> new EntityNotFoundException("User with id" +followingId+ " not found"));

        if(user1.getUserId() == user2.getUserId()){
            throw new IllegalArgumentException("You can`t follow yourself");
        }
        FollowKey followKey = new FollowKey(followerId, followingId);

        followRepository.save(new Follow(followKey, user1, user2));
    }


    public Set<Follow> getUsersFollowedBy(Long followerId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + followerId + " not found"));
        return follower.getFollowedByUser();
    }

    @Transactional
    public void deleteFollow(Long followerId, Long followingId) {
        userRepository.findById(followerId).orElseThrow(() -> new EntityNotFoundException("User with id " +followerId+ " not found"));
        userRepository.findById(followingId).orElseThrow(() -> new EntityNotFoundException("User with id " +followingId+ " not found"));

        FollowKey followKey = new FollowKey(followerId, followingId);
        followRepository.deleteById(followKey);
    }
}
