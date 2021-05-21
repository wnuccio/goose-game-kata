package goosegame.usecase.reset_game;

import goosegame.domain.CommandLine;

public class InterpretReset {
    private final ResetService resetService;

    public InterpretReset(ResetService resetService) {
        this.resetService = resetService;
    }

    public boolean doReset(CommandLine commandLine) {
        return commandLine.interpret("reset game", tokens -> resetService.doReset());
    }

    public boolean doStop(CommandLine commandLine) {
        return commandLine.interpret("stop game", tokens -> resetService.doStop());
    }
}
