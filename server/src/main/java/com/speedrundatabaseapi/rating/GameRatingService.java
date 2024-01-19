package com.speedrundatabaseapi.rating;

import com.speedrundatabaseapi.game.Game;
import com.speedrundatabaseapi.game.GameRepository;
import com.speedrundatabaseapi.user.User;
import com.speedrundatabaseapi.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service class for managing game ratings in the Speedrun Database API.
 *
 * <p>This class provides methods for adding or updating game ratings, and ensures the recalculation of the
 * average rating for associated games.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Service
public class GameRatingService {

    private final GameRatingRepository gameRatingRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    /**
     * Constructor for the GameRatingService class.
     *
     * @param gameRatingRepository The repository for managing game ratings.
     * @param gameRepository       The repository for managing games.
     * @param userRepository       The repository for managing users.
     */
    @Autowired
    public GameRatingService(GameRatingRepository gameRatingRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.gameRatingRepository = gameRatingRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    /**
     * Adds or updates a game rating for a user and a game, and recalculates the average rating for the associated game.
     *
     * @param userId The ID of the user providing the rating.
     * @param gameId The ID of the game being rated.
     * @param score  The score given to the game by the user.
     * @throws EntityNotFoundException If the user or game is not found.
     * @throws IllegalArgumentException If the provided score is not in the valid range (1-10).
     */
    @Transactional
    public void addGameRating(Long userId, Long gameId, int score) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found while adding rating to game"));
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game not found while adding rating to game"));

        if (score < 1 || score > 10) {
            throw new IllegalArgumentException("Score must be in the range of 1-10");
        }

        GameRatingKey ratingKey = new GameRatingKey(userId, gameId);

        Optional<GameRating> existingRating = gameRatingRepository.findById(ratingKey);
        if (existingRating.isPresent()) {
            GameRating ratingToUpdate = existingRating.get();
            ratingToUpdate.setScore(score);
            gameRatingRepository.save(ratingToUpdate);
        } else {
            GameRating newRating = new GameRating(ratingKey, user, game, score);
            gameRatingRepository.save(newRating);
        }

        game.recalculateAverageRating();
        gameRepository.save(game);
    }
}
