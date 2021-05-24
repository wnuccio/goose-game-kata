package app.domain.game;

import app.domain.interpreter.CommandLine;
import app.domain.interpreter.Interpreter;

public class InterpretResetGame implements Interpreter {
    private final StopOrResetGame stopOrResetGame;

    public InterpretResetGame(StopOrResetGame stopOrResetGame) {
        this.stopOrResetGame = stopOrResetGame;
    }

    public boolean interpret(CommandLine commandLine) {
        return commandLine.interpret("reset game", tokens -> stopOrResetGame.doReset());
    }
}
