package com.speedrundatabaseapi.game;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Game> getGames(){
        return gameService.getGames();
    }

    @PostMapping
    public void addNewGame(Game game){
        gameService.addNewGame(game);
    }

    @PutMapping(path = "/{gameId}/platform/{platformId}")
    public Game assignPlatformToGame(
            @PathVariable Long gameId,
            @PathVariable Long platformId
    ){
        return gameService.assignPlatformToGame(gameId, platformId);
    }
}
