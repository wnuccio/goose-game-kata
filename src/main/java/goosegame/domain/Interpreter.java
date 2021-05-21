package goosegame.domain;

import goosegame.boundary.input.CommandLine;

public interface Interpreter {
    boolean interpret(CommandLine commandLine);
}
