package com.speedrundatabaseapi.game;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/game")
public class GameController {

    private final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

    @GetMapping(path = "/{gameId}")
    public ResponseEntity<?> getGameDetails(@PathVariable Long gameId){
        try{
            Game game = gameService.getGameDetails(gameId);
            logger.info("Game details fetched successfully");
            return ResponseEntity.ok(game);
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while getting game details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting game details");
        }
    }

    @PostMapping
    public ResponseEntity<String> addNewGame(@RequestBody Game game){
        try {
            gameService.addNewGame(game);
            logger.info("Game added successfully");
            return ResponseEntity.ok("Game added successfully");
        } catch (Exception e) {
            logger.error("Error occurred while adding a new game");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding a new game");
        }
    }

    @PutMapping(path = "/{gameId}")
    public ResponseEntity<String> changeGameDetails(
            @PathVariable Long gameId,
            @RequestBody Game updatedGameDetails
    ){
        try{
            gameService.changeGameDetails(gameId, updatedGameDetails);
            logger.info("Game details updated successfully");
            return ResponseEntity.ok("Game details updated successfully");
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error while updating game details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating game details");
        }
    }

    @DeleteMapping(path = "/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable Long gameId) {
        try {
            gameService.deleteGame(gameId);
            logger.info("Game deleted successfully");
            return ResponseEntity.ok("Game deleted successfully");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while deleting game");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting game");
        }
    }

    @PutMapping(path = "/{gameId}/platform/{platformId}")
    public ResponseEntity<String> assignPlatformToGame(
            @PathVariable Long gameId,
            @PathVariable Long platformId
    ){
        try{
            gameService.assignPlatformToGame(gameId, platformId);
            logger.info("Platform assigned to game");
            return ResponseEntity.ok("Platform assigned to game");
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            logger.error("Error occurred while assigning platform to a game");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while assigning platform");
        }
    }
}
