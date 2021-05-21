package goosegame.domain;

import goosegame.application.CommandLine;

public interface Interpreter {
    boolean interpret(CommandLine commandLine);
}
