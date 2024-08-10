package dev.davivieira.application.ports.output;

import dev.davivieira.domain.entity.Note;
import java.util.List;

public interface NoteOutputPort {

    Note persistNote(Note note);

    List<Note> getNotes();
}
