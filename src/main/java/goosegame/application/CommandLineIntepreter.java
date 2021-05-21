package goosegame.application;

import goosegame.domain.Interpreter;

import java.util.List;

public class CommandLineIntepreter {
    private final List<Interpreter> intepreters;

    public CommandLineIntepreter(List<Interpreter> intepreters) {
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
