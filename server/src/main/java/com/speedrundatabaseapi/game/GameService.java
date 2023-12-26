package com.speedrundatabaseapi.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames(){
        return gameRepository.findAll();
    }

    public void addNewGame(Game game) {
        gameRepository.save(game);
    }
}
