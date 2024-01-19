package com.speedrundatabaseapi.game;

import com.speedrundatabaseapi.run.Run;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Controller class for handling HTTP requests related to Game entities.
 *
 * <p>This class defines endpoints for retrieving information about games, adding new games,
 * updating game details, deleting games, and assigning platforms to games.</p>
 *
 * <p>The class uses {@link GameService} to perform business logic related to games.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@RestController
@RequestMapping(path = "speedruns/api/games")
public class GameController {

    private final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Get a list of all games.
     *
     * @return A list of all games.
     */
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    /**
     * Get details of a specific game by its ID.
     *
     * @param gameId The ID of the game.
     * @return Details of the specified game.
     */
    @GetMapping(path = "/{gameId}")
    public ResponseEntity<?> getGameDetails(@PathVariable Long gameId) {
        try {
            Game game = gameService.getGameDetails(gameId);
            logger.info("Game details fetched successfully");
            return ResponseEntity.ok(game);
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while getting game details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting game details");
        }
    }

    /**
     * Get the runs associated with a specific game by its ID.
     *
     * @param gameId The ID of the game.
     * @return The set of runs associated with the specified game.
     */
    @GetMapping(path = "/{gameId}/runs")
    public ResponseEntity<?> getRunsInGame(@PathVariable Long gameId) {
        try {
            Set<Run> runsInGame = gameService.getRunsInGame(gameId);
            logger.info("Game runs fetched successfully");
            return ResponseEntity.ok(runsInGame);
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while getting game runs");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting game runs");
        }
    }

    /**
     * Add a new game.
     *
     * @param game The game to be added.
     * @return A message indicating the success of the operation.
     */
    @PostMapping
    public ResponseEntity<String> addNewGame(@RequestBody Game game) {
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

    /**
     * Update details of a specific game by its ID.
     *
     * @param gameId               The ID of the game to be updated.
     * @param updatedGameDetails   The updated details of the game.
     * @return A message indicating the success of the operation.
     */
    @PutMapping(path = "/{gameId}")
    public ResponseEntity<String> changeGameDetails(
            @PathVariable Long gameId,
            @RequestBody Game updatedGameDetails
    ) {
        try {
            gameService.changeGameDetails(gameId, updatedGameDetails);
            logger.info("Game details updated successfully");
            return ResponseEntity.ok("Game details updated successfully");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error while updating game details");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating game details");
        }
    }

    /**
     * Delete a specific game by its ID.
     *
     * @param gameId The ID of the game to be deleted.
     * @return A message indicating the success of the operation.
     */
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

    /**
     * Assign a platform to a specific game.
     *
     * @param gameId     The ID of the game.
     * @param platformId The ID of the platform to be assigned.
     * @return A message indicating the success of the operation.
     */
    @PutMapping(path = "/{gameId}/platform/{platformId}")
    public ResponseEntity<String> assignPlatformToGame(
            @PathVariable Long gameId,
            @PathVariable Long platformId
    ) {
        try {
            gameService.assignPlatformToGame(gameId, platformId);
            logger.info("Platform assigned to game");
            return ResponseEntity.ok("Platform assigned to game");
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while assigning platform to a game");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while assigning platform");
        }
    }
}
