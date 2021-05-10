package main;

import boundary.interpreters.Interpreter;
import usecase.reset_game.ApplicationSwitch;

public class GooseGameApp {

    private ApplicationSwitch applicationSwitch;
    private InputBoundary input;
    private Interpreter interpreter;

    public GooseGameApp(ApplicationSwitch applicationSwitch, InputBoundary input, Interpreter interpreter) {
        this.applicationSwitch = applicationSwitch;
        this.input = input;
        this.interpreter = interpreter;
    }

    public void run() {
        while(applicationSwitch.isOn()) {
            String line = input.readInputLine();
            if (line == null) continue;
            interpreter.acceptCommand(line);
        }
    }

}
