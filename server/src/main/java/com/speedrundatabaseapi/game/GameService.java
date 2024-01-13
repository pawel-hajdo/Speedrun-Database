package com.speedrundatabaseapi.game;

import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.platform.PlatformRepository;
import com.speedrundatabaseapi.run.Run;
import com.speedrundatabaseapi.run.RunRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public void addNewGame(Game game) {
        gameRepository.save(game);
    }

    public Game getGameDetails(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(()-> new EntityNotFoundException("Game not found"));
    }

    public void changeGameDetails(Long gameId, Game updatedGameDetails) {
        Game game = gameRepository.findById(gameId).orElseThrow(()-> new EntityNotFoundException("Game not found"));

        if(updatedGameDetails.getName() != null){
            game.setName(updatedGameDetails.getName());
        }
        if(updatedGameDetails.getDescription() != null){
            game.setDescription(updatedGameDetails.getDescription());
        }
        if(updatedGameDetails.getReleaseYear() != 0){
            game.setReleaseYear(updatedGameDetails.getReleaseYear());
        }
        if(updatedGameDetails.getImage() != null){
            game.setImage(updatedGameDetails.getImage());
        }

        gameRepository.save(game);
    }

    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }

    @Transactional
    public void assignPlatformToGame(Long gameId, Long platformId) {
        Game game = gameRepository.findById(gameId).orElseThrow(()-> new EntityNotFoundException("Game not found"));
        Platform platform = platformRepository.findById(platformId).orElseThrow(()-> new EntityNotFoundException("Platform not found"));

        Set<Platform> gameOnPlatforms = game.getGameOnPlatforms();
        gameOnPlatforms.add(platform);

        game.setGameOnPlatforms(gameOnPlatforms);
        gameRepository.save(game);
    }

    public Set<Run> getRunsInGame(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(()-> new EntityNotFoundException("Game not found"));

        return game.getRunsInGame();
    }
}
