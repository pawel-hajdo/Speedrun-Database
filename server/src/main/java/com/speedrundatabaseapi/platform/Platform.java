package com.speedrundatabaseapi.platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.speedrundatabaseapi.game.Game;
import com.speedrundatabaseapi.run.Run;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Represents a gaming platform in the Speedrun Database API.
 *
 * <p>A gaming platform is a specific device or system on which video games can be played. This class includes information
 * about the type, name, and the games assigned to the platform.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Entity
@Table
public class Platform {

    @Id
    @SequenceGenerator(
            name = "platform_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "platform_sequence"
    )
    @Column(name = "platform_id")
    private long platformId;

    @Column(name = "type")
    private PlatformType type;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "gameOnPlatforms")
    Set<Game> gamesAssignedToPlatform;

    @OneToMany(mappedBy = "platform")
    private Set<Run> runsOnPlatform;

    /**
     * Default constructor for the Platform class.
     */
    public Platform() {
    }

    /**
     * Parameterized constructor for the Platform class.
     *
     * @param type The type of the gaming platform.
     * @param name The name of the gaming platform.
     */
    public Platform(PlatformType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Constructor for creating a Platform object with a specified platform ID.
     *
     * @param platformId The ID of the gaming platform.
     */
    public Platform(long platformId) {
    }

    /**
     * Gets the ID of the gaming platform.
     *
     * @return The platform ID.
     */
    public long getPlatformId() {
        return platformId;
    }

    /**
     * Sets the ID of the gaming platform.
     *
     * @param platformId The platform ID to set.
     */
    public void setPlatformId(long platformId) {
        this.platformId = platformId;
    }

    /**
     * Gets the name of the gaming platform.
     *
     * @return The name of the platform.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the gaming platform.
     *
     * @param name The name of the platform to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the gaming platform.
     *
     * @return The type of the platform.
     */
    public PlatformType getType() {
        return type;
    }

    /**
     * Sets the type of the gaming platform.
     *
     * @param type The type of the platform to set.
     */
    public void setType(PlatformType type) {
        this.type = type;
    }

    /**
     * Gets the set of games assigned to the gaming platform.
     *
     * @return The set of games assigned to the platform.
     */
    public Set<Game> getGamesAssignedToPlatform() {
        return gamesAssignedToPlatform;
    }

    /**
     * Sets the set of games assigned to the gaming platform.
     *
     * @param gamesAssignedToPlatform The set of games to assign to the platform.
     */
    public void setGamesAssignedToPlatform(Set<Game> gamesAssignedToPlatform) {
        this.gamesAssignedToPlatform = gamesAssignedToPlatform;
    }
}
