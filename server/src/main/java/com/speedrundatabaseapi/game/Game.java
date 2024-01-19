package com.speedrundatabaseapi.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.rating.GameRating;
import com.speedrundatabaseapi.run.Run;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Entity class representing a video game in the Speedrun Database.
 *
 * <p>This class defines the structure of the 'game' table in the database and
 * includes various properties such as the game name, release year, description, image,
 * average rating, and associations with platforms, ratings, and runs.</p>
 *
 * <p>Note: The average rating is calculated dynamically using the ratings associated
 * with the game, and it is not directly stored in the database.</p>
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
     * Default constructor for the Game entity.
     */
    public Game() {
    }

    /**
     * Parameterized constructor for the Game entity.
     *
     * @param gameId        The unique identifier for the game.
     * @param name          The name of the game.
     * @param releaseYear   The release year of the game.
     * @param description   The description of the game.
     * @param image         The URL or path to the game's image.
     * @param averageRating The average rating of the game.
     */
    public Game(long gameId, String name, int releaseYear, String description, String image, Double averageRating) {
        this.gameId = gameId;
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.image = image;
        this.averageRating = averageRating;
    }

    /**
     * Recalculates the average rating based on the associated ratings.
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
     * Gets the unique identifier for the game.
     *
     * @return The game ID.
     */
    public long getGameId() {
        return gameId;
    }

    /**
     * Sets the unique identifier for the game.
     *
     * @param gameId The game ID to set.
     */
    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    /**
     * Gets the name of the game.
     *
     * @return The game name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the game.
     *
     * @param name The game name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the release year of the game.
     *
     * @return The release year.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year of the game.
     *
     * @param releaseYear The release year to set.
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the description of the game.
     *
     * @return The game description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the game.
     *
     * @param description The game description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the image URL or path of the game.
     *
     * @return The game image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image URL or path of the game.
     *
     * @param image The game image to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the set of platforms associated with the game.
     *
     * @return The set of platforms.
     */
    public Set<Platform> getGameOnPlatforms() {
        return gameOnPlatforms;
    }

    /**
     * Sets the set of platforms associated with the game.
     *
     * @param gameOnPlatforms The set of platforms to set.
     */
    public void setGameOnPlatforms(Set<Platform> gameOnPlatforms) {
        this.gameOnPlatforms = gameOnPlatforms;
    }

    /**
     * Gets the average rating of the game.
     *
     * @return The average rating.
     */
    public Double getAverageRating() {
        return averageRating;
    }

    /**
     * Sets the average rating of the game.
     *
     * @param averageRating The average rating to set.
     */
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * Gets the set of runs associated with the game.
     *
     * @return The set of runs.
     */
    public Set<Run> getRunsInGame() {
        return runsInGame;
    }
}
