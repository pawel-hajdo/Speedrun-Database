package com.speedrundatabaseapi.game;

import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.platform.PlatformRepository;
import com.speedrundatabaseapi.run.Run;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Service class for managing Game entities.
 *
 * <p>This class provides methods to perform various operations related to games, including
 * retrieving, adding, updating, and deleting games. It also includes functionality for
 * assigning platforms to games and fetching runs associated with a particular game.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;

    /**
     * Constructs a GameService with the specified repositories.
     *
     * @param gameRepository     The repository for Game entities.
     * @param platformRepository The repository for Platform entities.
     */
    @Autowired
    public GameService(GameRepository gameRepository, PlatformRepository platformRepository) {
        this.gameRepository = gameRepository;
        this.platformRepository = platformRepository;
    }

    /**
     * Retrieves a list of all games.
     *
     * @return A list of all games.
     */
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    /**
     * Adds a new game.
     *
     * @param game The game to be added.
     */
    public void addNewGame(Game game) {
        gameRepository.save(game);
    }

    /**
     * Retrieves details of a specific game.
     *
     * @param gameId The ID of the game.
     * @return The details of the specified game.
     * @throws EntityNotFoundException If the game with the given ID is not found.
     */
    public Game getGameDetails(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game not found"));
    }

    /**
     * Updates details of a specific game.
     *
     * @param gameId               The ID of the game to be updated.
     * @param updatedGameDetails   The updated details of the game.
     * @throws EntityNotFoundException If the game with the given ID is not found.
     */
    public void changeGameDetails(Long gameId, Game updatedGameDetails) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game not found"));

        if (updatedGameDetails.getName() != null) {
            game.setName(updatedGameDetails.getName());
        }
        if (updatedGameDetails.getDescription() != null) {
            game.setDescription(updatedGameDetails.getDescription());
        }
        if (updatedGameDetails.getReleaseYear() != 0) {
            game.setReleaseYear(updatedGameDetails.getReleaseYear());
        }
        if (updatedGameDetails.getImage() != null) {
            game.setImage(updatedGameDetails.getImage());
        }

        gameRepository.save(game);
    }

    /**
     * Deletes a specific game.
     *
     * @param gameId The ID of the game to be deleted.
     */
    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }

    /**
     * Assigns a platform to a game.
     *
     * @param gameId     The ID of the game.
     * @param platformId The ID of the platform to be assigned.
     * @throws EntityNotFoundException If the game or platform with the given IDs is not found.
     */
    @Transactional
    public void assignPlatformToGame(Long gameId, Long platformId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game not found"));
        Platform platform = platformRepository.findById(platformId).orElseThrow(() -> new EntityNotFoundException("Platform not found"));

        Set<Platform> gameOnPlatforms = game.getGameOnPlatforms();
        gameOnPlatforms.add(platform);

        game.setGameOnPlatforms(gameOnPlatforms);
        gameRepository.save(game);
    }

    /**
     * Retrieves the runs associated with a specific game.
     *
     * @param gameId The ID of the game.
     * @return The set of runs associated with the specified game.
     * @throws EntityNotFoundException If the game with the given ID is not found.
     */
    public Set<Run> getRunsInGame(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game not found"));

        return game.getRunsInGame();
    }
}
