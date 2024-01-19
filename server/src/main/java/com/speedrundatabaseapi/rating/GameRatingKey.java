package com.speedrundatabaseapi.rating;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Composite key class for the GameRating entity in the Speedrun Database API.
 *
 * <p>This class represents the composite primary key for the {@link GameRating} entity. It is used
 * to uniquely identify a game rating based on the combination of user ID and game ID.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see Embeddable
 * @see Column
 * @see Serializable
 * @see GameRating
 */
@Embeddable
public class GameRatingKey implements Serializable {

    @Column(name = "user_id")
    Long userId;
    @Column(name = "game_id")
    Long gameId;

    /**
     * Default constructor for the GameRatingKey class.
     */
    public GameRatingKey() {
    }

    /**
     * Parameterized constructor for the GameRatingKey class.
     *
     * @param userId The user ID associated with the game rating.
     * @param gameId The game ID associated with the game rating.
     */
    public GameRatingKey(Long userId, Long gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    /**
     * Get the user ID from the composite key.
     *
     * @return The user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set the user ID in the composite key.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get the game ID from the composite key.
     *
     * @return The game ID.
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * Set the game ID in the composite key.
     *
     * @param gameId The game ID to set.
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
