package com.ttb.wongnok.model.domain;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Difficulty {
    private long id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
}
