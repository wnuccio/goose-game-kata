package boundary.interpreters;

public abstract class Interpreter {
    private Interpreter nextInterpreter;

    public Interpreter(Interpreter nextInterpreter) {
        this.nextInterpreter = nextInterpreter;
    }

    public void acceptCommand(String commandLine) {
        if (nextInterpreter == null) return;
        nextInterpreter.acceptCommand(commandLine);
    }
}
