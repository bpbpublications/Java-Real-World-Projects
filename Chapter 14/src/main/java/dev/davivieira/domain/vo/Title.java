package dev.davivieira.domain.vo;

import lombok.Getter;

@Getter
public class Title {

    private final String name;

    private static final int MAX_TITLE_CHARACTERS = 120;

    private Title(String name) {
        if (name.length() > MAX_TITLE_CHARACTERS) {
            throw new IllegalArgumentException(STR."Title name exceeds maximum character limit \{MAX_TITLE_CHARACTERS}");
        }
        this.name = name;
    }

    public static Title of(String name) {
        return new Title(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
