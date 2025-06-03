package dev.boraxpr.quarkus.todo;

import java.time.LocalDateTime;

public record Todo(
    Integer id,
    String title,
    String description,
    boolean done,
    LocalDateTime created_at,
    LocalDateTime updated_at) {}
