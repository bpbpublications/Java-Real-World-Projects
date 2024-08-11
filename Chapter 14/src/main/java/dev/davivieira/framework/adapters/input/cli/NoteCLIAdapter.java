package dev.davivieira.framework.adapters.input.cli;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.davivieira.application.usecases.NoteUseCase;
import dev.davivieira.domain.entity.Note;
import dev.davivieira.domain.vo.Title;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class NoteCLIAdapter {

    private final NoteUseCase noteUseCase;

    public NoteCLIAdapter(NoteUseCase noteUseCase){
        this.noteUseCase = noteUseCase;
    }
    public String createNote(Scanner requestParams) {
        var noteParams = stdinParams(requestParams);
        var title = noteParams.get("title");
        var content = noteParams.get("content");
        noteUseCase.createNote(Title.of(title), content);
        return "Note created with success";
    }

    private Map<String, String> stdinParams(Scanner requestParams){
        Map<String, String> params = new HashMap<>();
        System.out.println("Provide the note title:");
        var title = requestParams.nextLine();
        params.put("title", title);
        System.out.println("Provide the note content:");
        var content = requestParams.nextLine();
        params.put("content", content);
        return params;
    }

    public void printNotes() {
        noteUseCase.getNotes().forEach(System.out::println);
    }
}
