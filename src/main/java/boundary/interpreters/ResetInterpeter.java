package boundary.interpreters;

import usecase.reset_game.ResetService;

public class ResetInterpeter extends Interpreter {
    private ResetService resetService;

    public ResetInterpeter(ResetService resetService, Interpreter nextInterpeter) {
        super(nextInterpeter);
        this.resetService = resetService;
    }

    public void acceptCommand(String commandLine) {
        CommandLineParser parser = new CommandLineParser(commandLine);

        if (parser.lineStartsWith("reset"))
            resetService.doReset();
        else if (parser.lineStartsWith("stop"))
            resetService.doStop();
        else
            super.acceptCommand(commandLine);
    }
}
