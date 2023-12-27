package com.speedrundatabaseapi.game;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/game")
public class GameController {

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
            return ResponseEntity.ok(game);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting game details");
        }
    }

    @PostMapping
    public ResponseEntity<String> addNewGame(Game game){
        try {
            gameService.addNewGame(game);
            return ResponseEntity.ok("Game added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding a new game");
        }
    }

    @PutMapping(path = "/{gameId}/platform/{platformId}")
    public ResponseEntity<String> assignPlatformToGame(
            @PathVariable Long gameId,
            @PathVariable Long platformId
    ){
        try{
            gameService.assignPlatformToGame(gameId, platformId);
            return ResponseEntity.ok("Platform assigned to game");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while assigning platform");
        }
    }
}
