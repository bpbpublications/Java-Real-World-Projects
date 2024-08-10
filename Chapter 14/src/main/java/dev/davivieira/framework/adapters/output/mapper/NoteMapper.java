package dev.davivieira.framework.adapters.output.mapper;

import dev.davivieira.domain.entity.Note;
import dev.davivieira.domain.vo.Id;
import dev.davivieira.domain.vo.Title;
import dev.davivieira.framework.adapters.output.data.NoteData;

public class NoteMapper {

    public static Note noteDataToDomain(NoteData noteData) {
        return Note.of(
                Id.withId(noteData.getId().toString()),
                Title.of(noteData.getTitle()),
                noteData.getContent(),
                noteData.getCreationTime()
        );
    }

    public static NoteData noteDomainToData(Note note){
        return NoteData.builder().
                id(note.getId().toString()).
                title(note.getTitle().getName()).
                content(note.getContent()).
                creationTime(note.getCreationTime())
                .build();
    }
}
