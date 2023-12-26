package com.speedrundatabaseapi.game;

import jakarta.persistence.*;

@Entity
@Table
public class game {

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
    private int releaseYear;
    private String description;
    private byte[] image;

    public game() {
    }

    public game(Long game_id, String name, int releaseYear, String description, byte[] image) {
        this.game_id = game_id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return game_id;
    }

    public void setId(Long game_id) {
        this.game_id = game_id;
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
}
