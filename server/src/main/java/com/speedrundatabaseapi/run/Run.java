package com.speedrundatabaseapi.run;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.speedrundatabaseapi.game.Game;
import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.user.User;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
public class Run {

    @Id
    @SequenceGenerator(
            name = "run_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "run_sequence"
    )
    @Column(name = "run_id")
    private long runId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @Column(name = "time")
    private Duration time;
    @Column(name = "type")
    private String type;
    @Column(name = "video_link")
    private String videoLink;
    @Column(name = "date")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;
    @Column(name = "confirmed_by")
    private long confirmedBy;

    public Run() {
    }

    public Run(long runId, User user, Game game, Duration time, String type, String videoLink, Platform platform, long confirmedBy) {
        this.runId = runId;
        this.user = user;
        this.game = game;
        this.time = time;
        this.type = type;
        this.videoLink = videoLink;
        this.platform = platform;
        this.confirmedBy = confirmedBy;
    }

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }

    public long getRunId() {
        return runId;
    }

    public void setRunId(long runId) {
        this.runId = runId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getConfirmedBy() {
        return confirmedBy;
    }

    public void setConfirmedBy(long confirmedBy) {
        this.confirmedBy = confirmedBy;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
