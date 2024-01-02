package com.speedrundatabaseapi.rating;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("speedruns/api/game-ratings")
public class GameRatingController {

    private final Logger logger = LoggerFactory.getLogger(GameRatingController.class);
    private final GameRatingService gameRatingService;

    @Autowired
    public GameRatingController(GameRatingService gameRatingService) {
        this.gameRatingService = gameRatingService;
    }

    @PostMapping()
    public ResponseEntity<String> addGameRating(@RequestBody GameRatingRequest gameRatingRequest){
        try{
            gameRatingService.addGameRating(gameRatingRequest.getUserId(), gameRatingRequest.getGameId(), gameRatingRequest.getScore());
            logger.info("Rating added to game successfully");
            return ResponseEntity.ok("Rating added successfully.");
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(IllegalArgumentException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while rating a game");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding rating");
        }
    }

}
