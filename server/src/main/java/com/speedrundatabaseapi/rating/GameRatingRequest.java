package com.speedrundatabaseapi.rating;

/**
 * Request class for creating or updating a game rating in the Speedrun Database API.
 *
 * <p>This class represents the request payload for adding or updating a game rating. It contains the user ID,
 * game ID, and the score given to the game by the user.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
public class GameRatingRequest {

    private Long userId;
    private Long gameId;
    private int score;

    /**
     * Default constructor for the GameRatingRequest class.
     */
    public GameRatingRequest() {
    }

    /**
     * Parameterized constructor for the GameRatingRequest class.
     *
     * @param userId The user ID associated with the game rating.
     * @param gameId The game ID associated with the game rating.
     * @param score  The score given to the game by the user.
     */
    public GameRatingRequest(Long userId, Long gameId, int score) {
        this.userId = userId;
        this.gameId = gameId;
        this.score = score;
    }

    /**
     * Get the user ID from the game rating request.
     *
     * @return The user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set the user ID in the game rating request.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get the game ID from the game rating request.
     *
     * @return The game ID.
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * Set the game ID in the game rating request.
     *
     * @param gameId The game ID to set.
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * Get the score from the game rating request.
     *
     * @return The score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the score in the game rating request.
     *
     * @param score The score to set.
     */
    public void setScore(int score) {
        this.score = score;
    }
}
