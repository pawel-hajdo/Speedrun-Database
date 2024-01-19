package com.speedrundatabaseapi.follow;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Controller class for handling Follow-related HTTP requests.
 *
 * <p>The FollowController class provides RESTful endpoints for managing follows between users,
 * including following a user, fetching users followed by a specific user, and unfollowing a user.</p>
 *
 * <p>The class is annotated with Spring's RESTful annotations to define request mappings,
 * and it uses the FollowService to handle business logic.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@RestController
@RequestMapping(path = "speedruns/api/follows")
public class FollowController {

    private final FollowService followService;
    private final Logger logger = LoggerFactory.getLogger(FollowController.class);

    /**
     * Constructor for the FollowController class.
     *
     * @param followService The FollowService used for handling follow-related operations.
     */
    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    /**
     * Endpoint to get a list of all follows.
     *
     * @return List of Follow entities representing all follows.
     */
    @GetMapping
    public List<Follow> getAllFollows() {
        return followService.getAllFollows();
    }

    /**
     * Endpoint to follow a user.
     *
     * @param followRequest The request body containing followerId and followingId.
     * @return ResponseEntity indicating the success or failure of the follow operation.
     */
    @PostMapping
    public ResponseEntity<String> followUser(@RequestBody FollowRequest followRequest) {
        try {
            followService.followUser(followRequest.getFollowerId(), followRequest.getFollowingId());
            logger.info("User followed successfully");
            return ResponseEntity.ok("User followed successfully");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            logger.info("Tried to follow user that is already followed by user");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tried to follow user that is already followed by user");
        } catch (IllegalArgumentException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while following a user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while following a user");
        }
    }

    /**
     * Endpoint to get the users followed by a specific user.
     *
     * @param followerId The ID of the follower user.
     * @return ResponseEntity containing a Set of Follow entities representing users followed by the specified user.
     */
    @GetMapping(path = "/{followerId}")
    public ResponseEntity<?> getUsersFollowedBy(@PathVariable Long followerId) {
        try {
            Set<Follow> follows = followService.getUsersFollowedBy(followerId);
            logger.info("Successfully fetched users followed by user with id " + followerId);
            return ResponseEntity.ok(follows);
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while fetching users followed by user with id " + followerId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching users followed by user with id " + followerId);
        }
    }

    /**
     * Endpoint to unfollow a user.
     *
     * @param followerId  The ID of the follower user.
     * @param followingId The ID of the user to unfollow.
     * @return ResponseEntity indicating the success or failure of the unfollow operation.
     */
    @DeleteMapping("/{followerId}/following/{followingId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        try {
            followService.deleteFollow(followerId, followingId);
            logger.info("User unfollowed successfully");
            return ResponseEntity.ok("User unfollowed successfully");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while unfollowing user with id " + followingId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while unfollowing user with id " + followingId);
        }
    }
}
