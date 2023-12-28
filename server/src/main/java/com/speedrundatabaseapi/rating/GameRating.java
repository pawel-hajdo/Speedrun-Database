package com.speedrundatabaseapi.rating;

import com.speedrundatabaseapi.game.Game;
import com.speedrundatabaseapi.user.User;
import jakarta.persistence.*;

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

    public GameRating() {
    }

    public GameRating(GameRatingKey id, User user, Game game, int score) {
        this.id = id;
        this.user = user;
        this.game = game;
        this.score = score;
    }

    public GameRatingKey getId() {
        return id;
    }

    public void setId(GameRatingKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
