package com.speedrundatabaseapi.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.rating.GameRating;
import com.speedrundatabaseapi.run.Run;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Entity class representing a video game.
 *
 * <p>This class is mapped to the "game" table in the database and includes information
 * about a video game, such as its name, release year, description, image, and average rating.</p>
 *
 * <p>It also defines relationships with other entities, such as platforms, ratings, and runs.</p>
 *
 * <p>The average rating is calculated based on the ratings given by users.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Entity
@Table
public class Game {

    @Id
    @SequenceGenerator(
            name = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    @Column(name = "game_id")
    private long gameId;

    @Column(name = "name")
    private String name;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "average_rating", columnDefinition = "NUMERIC(4,2)")
    private Double averageRating;

    @ManyToMany
    @JoinTable(
            name = "game_on_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<Platform> gameOnPlatforms;

    @OneToMany(mappedBy = "game")
    private Set<GameRating> ratings;

    @OneToMany(mappedBy = "game")
    @JsonIgnore
    private Set<Run> runsInGame;

    /**
     * Recalculate the average rating of the game based on user ratings.
     */
    public void recalculateAverageRating() {
        if (ratings != null && !ratings.isEmpty()) {
            double sum = ratings.stream().mapToInt(GameRating::getScore).sum();
            this.averageRating = sum / ratings.size();
        } else {
            this.averageRating = null;
        }
    }

    /**
     * Get the unique identifier of the game.
     *
     * @return The game's unique identifier.
     */
    public long getGameId() {
        return gameId;
    }

    /**
     * Set the unique identifier of the game.
     *
     * @param gameId The new unique identifier for the game.
     */
    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    /**
     * Get the name of the game.
     *
     * @return The name of the game.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the game.
     *
     * @param name The new name for the game.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the release year of the game.
     *
     * @return The release year of the game.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Set the release year of the game.
     *
     * @param releaseYear The new release year for the game.
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Get the description of the game.
     *
     * @return The description of the game.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the game.
     *
     * @param description The new description for the game.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the image URL of the game.
     *
     * @return The image URL of the game.
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the image URL of the game.
     *
     * @param image The new image URL for the game.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Get the average rating of the game.
     *
     * @return The average rating of the game.
     */
    public Double getAverageRating() {
        return averageRating;
    }

    /**
     * Set the average rating of the game.
     *
     * @param averageRating The new average rating for the game.
     */
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * Get the set of platforms on which the game is available.
     *
     * @return The set of platforms on which the game is available.
     */
    public Set<Platform> getGameOnPlatforms() {
        return gameOnPlatforms;
    }

    /**
     * Set the set of platforms on which the game is available.
     *
     * @param gameOnPlatforms The new set of platforms for the game.
     */
    public void setGameOnPlatforms(Set<Platform> gameOnPlatforms) {
        this.gameOnPlatforms = gameOnPlatforms;
    }

    /**
     * Get the set of ratings given to the game by users.
     *
     * @return The set of ratings given to the game by users.
     */
    public Set<GameRating> getRatings() {
        return ratings;
    }

    /**
     * Set the set of ratings given to the game by users.
     *
     * @param ratings The new set of ratings for the game.
     */
    public void setRatings(Set<GameRating> ratings) {
        this.ratings = ratings;
    }

    /**
     * Get the set of runs associated with the game.
     *
     * @return The set of runs associated with the game.
     */
    public Set<Run> getRunsInGame() {
        return runsInGame;
    }

    /**
     * Set the set of runs associated with the game.
     *
     * @param runsInGame The new set of runs for the game.
     */
    public void setRunsInGame(Set<Run> runsInGame) {
        this.runsInGame = runsInGame;
    }
}
