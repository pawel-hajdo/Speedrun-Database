package com.speedrundatabaseapi.follow;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/follow")
public class FollowController {

    private final FollowService followService;
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
            return ResponseEntity.ok("User followed successfully");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while following a user");
        }
    }
}
