package goosegame.boundary.input;

import java.util.List;

public class CommandsInterpreter {
    private final List<Interpreter> intepreters;

    public CommandsInterpreter(List<Interpreter> intepreters) {
        this.intepreters = intepreters;
    }

    public void acceptCommand(String line) {
        CommandLine commandLine = new CommandLine(line);

        for (Interpreter i : this.intepreters) {
            if (i.interpret(commandLine)) return;
        }
    }

    private boolean unrecognizedCommand(CommandLine commandLine) {
        return false;
    }
}
