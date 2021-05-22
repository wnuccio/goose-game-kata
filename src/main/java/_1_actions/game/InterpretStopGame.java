package _1_actions.game;

import _1_actions.interpreter.CommandLine;
import _1_actions.interpreter.Interpreter;

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
