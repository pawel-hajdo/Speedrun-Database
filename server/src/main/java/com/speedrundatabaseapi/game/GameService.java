package com.speedrundatabaseapi.game;

import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.platform.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;
    @Autowired
    public GameService(GameRepository gameRepository, PlatformRepository platformRepository) {
        this.gameRepository = gameRepository;
        this.platformRepository = platformRepository;
    }

    public List<Game> getGames(){
        return gameRepository.findAll();
    }

    public void addNewGame(Game game) {
        gameRepository.save(game);
    }

    public Game assignPlatformToGame(Long gameId, Long platformId) {
        Game game = gameRepository.findById(gameId).get();
        Platform platform = platformRepository.findById(platformId).get();

        Set<Platform> gameOnPlatforms = game.getGameOnPlatforms();
        gameOnPlatforms.add(platform);

        game.setGameOnPlatforms(gameOnPlatforms);
        return gameRepository.save(game);
    }
}
