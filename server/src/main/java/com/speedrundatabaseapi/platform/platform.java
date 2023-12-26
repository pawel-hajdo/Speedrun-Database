package com.speedrundatabaseapi.platform;

import jakarta.persistence.*;

@Entity
@Table
public class platform {

    @Id
    @SequenceGenerator(
            name = "platform_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "platform_sequence"
    )
    private long platform_id;
    private String name;
    private String type;

    public platform() {
    }

    public platform(long platform_id, String name, String type) {
        this.platform_id = platform_id;
        this.name = name;
        this.type = type;
    }

    public long getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(long platform_id) {
        this.platform_id = platform_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
