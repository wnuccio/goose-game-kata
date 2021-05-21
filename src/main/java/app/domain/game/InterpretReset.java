package app.domain.game;

import app.domain.command.CommandLine;

public class InterpretReset {
    private final ResetGame resetGame;

    public InterpretReset(ResetGame resetGame) {
        this.resetGame = resetGame;
    }

    public boolean doReset(CommandLine commandLine) {
        return commandLine.interpret("reset game", tokens -> resetGame.doReset());
    }

    public boolean doStop(CommandLine commandLine) {
        return commandLine.interpret("stop game", tokens -> resetGame.doStop());
    }
}
