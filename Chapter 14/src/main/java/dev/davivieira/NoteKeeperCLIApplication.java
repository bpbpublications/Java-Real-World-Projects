package dev.davivieira;

import dev.davivieira.framework.adapters.input.cli.NoteCLIAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Scanner;

@Component
@ConditionalOnNotWebApplication
public class NoteKeeperCLIApplication implements CommandLineRunner {

    private final NoteCLIAdapter noteCLIAdapter;

    public NoteKeeperCLIApplication(NoteCLIAdapter noteCLIAdapter) {
        this.noteCLIAdapter = noteCLIAdapter;
    }

    @Override
    public void run(String... args) {
        var operation = args[0];
        Scanner scanner = new Scanner(System.in);
        switch (operation) {
            case "createNote" ->noteCLIAdapter.createNote(scanner);
            case "getNotes" -> noteCLIAdapter.printNotes();
            default -> throw new InvalidParameterException("The supported operations are: createNote and getNotes");
        }
    }
}
