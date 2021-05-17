package goosegame.boundary.interpreters;

public abstract class Interpreter {
    private final Interpreter nextInterpreter;

    public Interpreter(Interpreter nextInterpreter) {
        this.nextInterpreter = nextInterpreter;
    }

    public void acceptCommand(String commandLine) {
        if (nextInterpreter == null) return;
        nextInterpreter.acceptCommand(commandLine);
    }
}
