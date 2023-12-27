package com.speedrundatabaseapi.game;

import com.speedrundatabaseapi.platform.Platform;
import jakarta.persistence.*;

import java.util.Set;

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
    private byte[] image;

    @ManyToMany
    @JoinTable(
            name = "game_on_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    Set<Platform> gameOnPlatforms;
    public Game() {
    }

    public Game(long gameId, String name, int releaseYear, String description, byte[] image) {
        this.gameId = gameId;
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.image = image;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<Platform> getGameOnPlatforms() {
        return gameOnPlatforms;
    }

    public void setGameOnPlatforms(Set<Platform> gameOnPlatforms) {
        this.gameOnPlatforms = gameOnPlatforms;
    }
}
