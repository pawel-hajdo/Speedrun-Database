package com.speedrundatabaseapi.rating;

import com.speedrundatabaseapi.game.Game;
import com.speedrundatabaseapi.user.User;
import jakarta.persistence.*;

/**
 * Entity class representing the ratings given by users to games in the Speedrun Database.
 *
 * <p>This class is mapped to the "rating" table in the database and uses a composite primary key
 * ({@link GameRatingKey}) to uniquely identify each rating based on the user and game IDs.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see Entity
 * @see Table
 * @see EmbeddedId
 * @see ManyToOne
 * @see MapsId
 * @see JoinColumn
 * @see Column
 * @see GameRatingKey
 * @see User
 * @see Game
 */
@Entity
@Table(name = "rating")
public class GameRating {

    @EmbeddedId
    private GameRatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "score")
    private int score;

    /**
     * Default constructor for the GameRating class.
     */
    public GameRating() {
    }

    /**
     * Parameterized constructor for creating a GameRating instance with specified attributes.
     *
     * @param id    The composite primary key for the rating.
     * @param user  The user who gave the rating.
     * @param game  The game being rated.
     * @param score The score assigned to the game by the user.
     */
    public GameRating(GameRatingKey id, User user, Game game, int score) {
        this.id = id;
        this.user = user;
        this.game = game;
        this.score = score;
    }

    /**
     * Getter method for retrieving the composite primary key of the GameRating.
     *
     * @return The composite primary key.
     */
    public GameRatingKey getId() {
        return id;
    }

    /**
     * Setter method for setting the composite primary key of the GameRating.
     *
     * @param id The composite primary key to set.
     */
    public void setId(GameRatingKey id) {
        this.id = id;
    }

    /**
     * Getter method for retrieving the user who gave the rating.
     *
     * @return The user who gave the rating.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter method for setting the user who gave the rating.
     *
     * @param user The user who gave the rating.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter method for retrieving the game being rated.
     *
     * @return The game being rated.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter method for setting the game being rated.
     *
     * @param game The game being rated.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Getter method for retrieving the score assigned to the game by the user.
     *
     * @return The score assigned to the game.
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter method for setting the score assigned to the game by the user.
     *
     * @param score The score to set.
     */
    public void setScore(int score) {
        this.score = score;
    }
}
