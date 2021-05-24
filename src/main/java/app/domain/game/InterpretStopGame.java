package app.domain.game;

import app.domain.interpreter.CommandLine;
import app.domain.interpreter.Interpreter;

public class InterpretStopGame implements Interpreter {

    private final StopOrResetGame stopOrResetGame;

    public InterpretStopGame(StopOrResetGame stopOrResetGame) {
        this.stopOrResetGame = stopOrResetGame;
    }

    @Override
    public boolean interpret(CommandLine commandLine) {
        return commandLine.interpret("stop game", tokens -> stopOrResetGame.doStop());
    }
}
