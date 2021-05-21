package app.domain.command;

public interface Interpreter {
    boolean interpret(CommandLine commandLine);
}
