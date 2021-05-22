package _1_actions.game;

import _1_actions.interpreter.CommandLine;
import _1_actions.interpreter.Interpreter;

public class InterpretResetGame implements Interpreter {
    private final StopOrResetGame stopOrResetGame;

    public InterpretResetGame(StopOrResetGame stopOrResetGame) {
        this.stopOrResetGame = stopOrResetGame;
    }

    public boolean interpret(CommandLine commandLine) {
        return commandLine.interpret("reset game", tokens -> stopOrResetGame.doReset());
    }
}
