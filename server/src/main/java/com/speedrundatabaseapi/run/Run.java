package com.speedrundatabaseapi.run;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.speedrundatabaseapi.game.Game;
import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.user.User;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Entity class representing a run in the Speedrun Database API.
 *
 * <p>This class is annotated with JPA annotations for entity mapping and includes custom JSON serialization and deserialization annotations.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see RunCustomSerializer
 * @see RunCustomDeserializer
 */
@Entity
@Table
@JsonSerialize(using = RunCustomSerializer.class)
@JsonDeserialize(using = RunCustomDeserializer.class)
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

    /**
     * Default constructor for the Run class.
     */
    public Run() {
    }

    /**
     * Parameterized constructor for the Run class.
     *
     * @param runId        The ID of the run.
     * @param user         The user associated with the run.
     * @param game         The game associated with the run.
     * @param time         The duration of the run.
     * @param type         The type of the run.
     * @param videoLink    The link to the video of the run.
     * @param platform     The platform on which the run was performed.
     * @param confirmedBy  The ID of the user who confirmed the run.
     */
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

    /**
     * Callback method executed before the entity is persisted.
     * Sets the creation date of the run.
     */
    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }

    // Getters and setters for the fields

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public long getConfirmedBy() {
        return confirmedBy;
    }

    public void setConfirmedBy(long confirmedBy) {
        this.confirmedBy = confirmedBy;
    }
}
