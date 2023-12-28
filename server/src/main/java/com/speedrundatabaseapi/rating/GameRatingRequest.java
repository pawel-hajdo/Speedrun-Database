package com.speedrundatabaseapi.rating;

public class GameRatingRequest {

    private Long userId;
    private Long gameId;
    private int score;

    public GameRatingRequest() {
    }

    public GameRatingRequest(Long userId, Long gameId, int score) {
        this.userId = userId;
        this.gameId = gameId;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
