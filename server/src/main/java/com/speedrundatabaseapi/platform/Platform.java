package com.speedrundatabaseapi.platform;

import jakarta.persistence.*;

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

    public Platform() {
    }

    public Platform( PlatformType type, String name) {
        this.type = type;
        this.name = name;
    }

    public long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(long platformId) {
        this.platformId = platformId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlatformType getType() {
        return type;
    }

    public void setType(PlatformType type) {
        this.type = type;
    }
}
