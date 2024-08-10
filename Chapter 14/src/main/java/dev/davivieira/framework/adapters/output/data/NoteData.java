package dev.davivieira.framework.adapters.output.data;

import dev.davivieira.domain.vo.Title;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "note")
public class NoteData {

    @Id
    private String id;

    private String title;

    private String content;

    private Instant creationTime;
}
