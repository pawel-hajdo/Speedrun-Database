package com.speedrundatabaseapi.rating;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class handling HTTP requests related to game ratings in the Speedrun Database API.
 *
 * <p>This class is responsible for handling RESTful endpoints related to game ratings, such as adding
 * a new rating for a game. It communicates with the {@link GameRatingService} to perform the necessary
 * business logic for game ratings.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see RestController
 * @see RequestMapping
 * @see Autowired
 * @see LoggerFactory
 * @see ResponseEntity
 * @see GameRatingService
 * @see GameRatingRequest
 */
@RestController
@RequestMapping("speedruns/api/game-ratings")
public class GameRatingController {

    private final Logger logger = LoggerFactory.getLogger(GameRatingController.class);
    private final GameRatingService gameRatingService;

    /**
     * Constructor for the GameRatingController class.
     *
     * @param gameRatingService The service responsible for handling game rating operations.
     */
    @Autowired
    public GameRatingController(GameRatingService gameRatingService) {
        this.gameRatingService = gameRatingService;
    }

    /**
     * Handles HTTP POST requests to add a new rating for a game.
     *
     * @param gameRatingRequest The request object containing user ID, game ID, and the score to be added.
     * @return ResponseEntity indicating the status of the operation.
     */
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
