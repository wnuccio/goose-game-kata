package app.domain.game;

import app.domain.interpreter.CommandLine;
import app.domain.interpreter.Interpreter;

public class InterpretStopGame implements Interpreter {

    private Game game;

    public InterpretStopGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean interpret(CommandLine commandLine) {
        return commandLine.interpret("stop game", tokens -> game.stop());
    }
}
