package dev.davivieira.application.ports.input;

import dev.davivieira.application.ports.output.NoteOutputPort;
import dev.davivieira.application.usecases.NoteUseCase;
import dev.davivieira.domain.entity.Note;
import dev.davivieira.domain.vo.Title;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteInputPort implements NoteUseCase {

    private final NoteOutputPort noteOutputPort;

    public NoteInputPort (NoteOutputPort noteOutputPort) {
        this.noteOutputPort = noteOutputPort;
    }

    @Override
    public void createNote(Title title, String content) {
        var note = Note.of(title, content);
        persistNote(note);
    }

    private void persistNote(Note note) {
        noteOutputPort.persistNote(note);
    }

    @Override
    public List<Note> getNotes() {
        return noteOutputPort.getNotes();
    }
}
