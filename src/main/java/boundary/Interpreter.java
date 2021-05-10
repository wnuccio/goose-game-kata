package boundary;

public abstract class Interpreter {
    private Interpreter nextInterpeter;

    public Interpreter(Interpreter nextInterpeter) {
        this.nextInterpeter = nextInterpeter;
    }

    public void acceptCommand(String commandLine) {
        if (nextInterpeter == null) return;
        nextInterpeter.acceptCommand(commandLine);
    }
}
