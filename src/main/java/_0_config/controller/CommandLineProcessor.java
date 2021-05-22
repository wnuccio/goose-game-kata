package _0_config.controller;

import _2_domain.interpreter.CommandLine;
import _2_domain.interpreter.Interpreter;

import java.util.List;

public class CommandLineProcessor {
    private final List<Interpreter> intepreters;

    public CommandLineProcessor(List<Interpreter> intepreters) {
        this.intepreters = intepreters;
    }

    public void acceptCommand(String line) {
        CommandLine commandLine = new CommandLine(line);

        for (Interpreter i : this.intepreters) {
            if (i.interpret(commandLine)) return;
        }
    }

    public static final Interpreter ignoreUnrecognizedCommands = (commandLine -> false);
}
