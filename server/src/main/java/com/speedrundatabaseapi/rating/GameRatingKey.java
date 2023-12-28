package com.speedrundatabaseapi.rating;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class GameRatingKey implements Serializable {

    @Column(name = "user_id")
    Long userId;
    @Column(name = "game_id")
    Long gameId;

    public GameRatingKey() {
    }

    public GameRatingKey(Long userId, Long gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
