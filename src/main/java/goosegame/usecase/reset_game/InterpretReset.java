package goosegame.usecase.reset_game;

import goosegame.application.CommandLine;

public class InterpretReset {
    private final ResetService resetService;

    public InterpretReset(ResetService resetService) {
        this.resetService = resetService;
    }

    public boolean doReset(CommandLine commandLine) {
        return commandLine.interpret("reset game", resetService::doReset);
    }

    public boolean doStop(CommandLine commandLine) {
        return commandLine.interpret("stop game", resetService::doStop);
    }
}
