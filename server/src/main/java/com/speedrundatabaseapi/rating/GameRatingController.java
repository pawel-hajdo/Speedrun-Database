package com.speedrundatabaseapi.rating;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game-ratings")
public class GameRatingController {

    private final GameRatingService gameRatingService;

    @Autowired
    public GameRatingController(GameRatingService gameRatingService) {
        this.gameRatingService = gameRatingService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addGameRating(@RequestBody GameRatingRequest gameRatingRequest){
        try{
            gameRatingService.addGameRating(gameRatingRequest.getUserId(), gameRatingRequest.getGameId(), gameRatingRequest.getScore());
            return ResponseEntity.ok("Rating added successfully.");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding rating");
        }
    }

}
