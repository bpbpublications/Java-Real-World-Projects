package dev.davivieira.domain.entity;

import dev.davivieira.domain.vo.Id;
import dev.davivieira.domain.vo.Title;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Builder
@Getter
@ToString
public class Note {

    private Id id;

    private Title title;

    private String content;

    private Instant creationTime;

    private Note(Id id, Title title, String content, Instant creationTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationTime = creationTime;
    }

    public static Note of(Title title, String content) {
        return new Note(Id.withoutId(), title, content, Instant.now());
    }

    public static Note of(Id id, Title title, String content, Instant creationTime) {
        return new Note(id, title, content, creationTime);
    }
}
