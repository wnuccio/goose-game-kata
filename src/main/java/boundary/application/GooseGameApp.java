package boundary.application;

import boundary.interpreters.Interpreter;
import usecase.reset_game.ApplicationSwitch;

public class GooseGameApp {

    private ApplicationSwitch applicationSwitch;
    private InputBoundary inputOutput;
    private Interpreter interpreter;

    public GooseGameApp(ApplicationSwitch applicationSwitch, InputOutput inputOutput, Interpreter interpreter) {
        this.applicationSwitch = applicationSwitch;
        this.inputOutput = inputOutput;
        this.interpreter = interpreter;
    }

    public void run() {
        while(applicationSwitch.isOn()) {
            String line = inputOutput.readInputLine();
            interpreter.acceptCommand(line);
        }
    }

}
