package dev.davivieira.domain.vo;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Id {

    private final UUID uuid;

    private Id(UUID uuid) {
        this.uuid = uuid;
    }

    public static Id withId(String id) {
        return new Id(UUID.fromString(id));
    }

    public static Id withoutId() {
        return new Id(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}