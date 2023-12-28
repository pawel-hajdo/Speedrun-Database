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

@Service
public class GameRatingService {

    private final GameRatingRepository gameRatingRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    @Autowired
    public GameRatingService(GameRatingRepository gameRatingRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.gameRatingRepository = gameRatingRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public void addGameRating(Long userId, Long gameId, int score){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found while adding rating to game"));
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game not found while adding rating to game"));

        GameRatingKey ratingKey = new GameRatingKey(userId, gameId);

        Optional<GameRating> existingRating = gameRatingRepository.findById(ratingKey);
        if(existingRating.isPresent()){
            GameRating ratingToUpdate = existingRating.get();
            ratingToUpdate.setScore(score);
            gameRatingRepository.save(ratingToUpdate);
        }else{
            GameRating newRating = new GameRating(ratingKey, user, game, score);
            gameRatingRepository.save(newRating);
        }

        game.recalculateAverageRating();
        gameRepository.save(game);
    }
}
