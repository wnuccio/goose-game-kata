package app.domain.game;

import app.domain.command.CommandLine;
import app.domain.command.Interpreter;

import java.util.List;

public class CommandLineProcessor {
    private final List<Interpreter> intepreters;

    public CommandLineProcessor(List<Interpreter> intepreters) {
        this.intepreters = intepreters;
    }

    public void acceptCommand(String line) {
        CommandLine commandLine = new TokenizableCommandLine(line);

        for (Interpreter i : this.intepreters) {
            if (i.interpret(commandLine)) return;
        }
    }

    public static final Interpreter ignoreUnrecognizedCommands = (commandLine -> false);
}
