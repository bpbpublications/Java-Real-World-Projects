package dev.davivieira.framework.adapters.output;

import dev.davivieira.application.ports.output.NoteOutputPort;
import dev.davivieira.domain.entity.Note;
import dev.davivieira.framework.adapters.output.mapper.NoteMapper;
import dev.davivieira.framework.adapters.output.repository.NoteRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteH2Adapter implements NoteOutputPort {

    private final NoteRepository noteRepository;

    public NoteH2Adapter(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note persistNote(Note note) {
        var noteData = NoteMapper.noteDomainToData(note);
        var persistedNoteData = noteRepository.save(noteData);
        return NoteMapper.noteDataToDomain(persistedNoteData);
    }

    @Override
    public List<Note> getNotes() {
        var allNoteData = noteRepository.findAll();
        var notes = new ArrayList<Note>();
        allNoteData.forEach(noteData -> {
            var note = NoteMapper.noteDataToDomain(noteData);
            notes.add(note);
        });
        return notes;
    }
}
