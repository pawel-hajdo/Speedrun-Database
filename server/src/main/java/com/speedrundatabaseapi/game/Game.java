package com.speedrundatabaseapi.game;

import jakarta.persistence.*;

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
    private long game_id;
    private String name;
    private int release_year;
    private String description;
    private byte[] image;

    public Game() {
    }

    public Game(long game_id, String name, int release_year, String description, byte[] image) {
        this.game_id = game_id;
        this.name = name;
        this.release_year = release_year;
        this.description = description;
        this.image = image;
    }
    public long getGame_id() {
        return game_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
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
}
