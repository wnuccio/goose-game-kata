package goosegame.boundary.application;

import goosegame.boundary.interpreters.Interpreter;
import goosegame.usecase.reset_game.ApplicationSwitch;

public class GooseGameApp {

    private final ApplicationSwitch applicationSwitch;
    private final InputBoundary inputOutput;
    private final Interpreter interpreter;

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
