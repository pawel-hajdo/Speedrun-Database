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

@RestController
@RequestMapping(path = "/follow")
public class FollowController {

    private final FollowService followService;
    private final Logger logger = LoggerFactory.getLogger(FollowController.class);
    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping
    public List<Follow> getAllFollows(){
        return followService.getAllFollows();
    }

    @PostMapping
    public ResponseEntity<String> followUser(@RequestBody FollowRequest followRequest){
        try{
            followService.followUser(followRequest.getFollowerId(), followRequest.getFollowingId());
            logger.info("User followed successfully");
            return ResponseEntity.ok("User followed successfully");
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (DataIntegrityViolationException e){
            logger.info("Tried to follow user that is already followed by user");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tried to follow user that is already followed by user");
        }catch (IllegalArgumentException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while adding a new user");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while following a user");
        }
    }
}
