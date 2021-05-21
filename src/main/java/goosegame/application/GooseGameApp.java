package goosegame.application;

import goosegame.usecase.reset_game.ApplicationSwitch;

public class GooseGameApp {

    private final ApplicationSwitch applicationSwitch;
    private final InputBoundary input;
    private final CommandLineIntepreter interpreter;

    public GooseGameApp(ApplicationSwitch applicationSwitch, InputBoundary input, CommandLineIntepreter interpreter) {
        this.applicationSwitch = applicationSwitch;
        this.input = input;
        this.interpreter = interpreter;
    }

    public void run() {
        while(applicationSwitch.isOn()) {
            String line = input.readInputLine();
            interpreter.acceptCommand(line);
        }
    }

}
