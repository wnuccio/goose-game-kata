package app.domain.game;

import app.domain.interpreter.CommandLine;
import app.domain.interpreter.Interpreter;

public class InterpretResetGame implements Interpreter {
    private Game game;

    public InterpretResetGame(Game game) {
        this.game = game;
    }

    public boolean interpret(CommandLine commandLine) {
        return commandLine.interpret("reset game", tokens -> game.reset());
    }
}
