package dev.davivieira.application.usecases;

import dev.davivieira.domain.entity.Note;
import dev.davivieira.domain.vo.Title;

import java.util.List;

public interface NoteUseCase {

    void createNote(Title title, String content);

    List<Note> getNotes();
}
