package dev.davivieira.framework.adapters.input.rest;

import dev.davivieira.application.usecases.NoteUseCase;
import dev.davivieira.domain.entity.Note;
import dev.davivieira.domain.vo.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteRestAdapter {

    private final NoteUseCase noteUseCase;

    @Autowired
    NoteRestAdapter(NoteUseCase noteUseCase) {
        this.noteUseCase = noteUseCase;
    }

    @PostMapping("/note")
    private void addPerson(@RequestBody NotePayload notePayload) {
        noteUseCase.createNote(Title.of(notePayload.title()), notePayload.content());
    }

    @GetMapping("/notes")
    private List<Note> all() {
        return noteUseCase.getNotes();
    }
}
