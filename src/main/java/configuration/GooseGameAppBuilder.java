package configuration;

import add_player.AddPlayerUseCase;
import add_player.OutputBoundary;
import add_player.Players;
import configuration_test.TestSystemInputOuput;

public class GooseGameAppBuilder {
    private String[] args;

    public GooseGameAppBuilder(String[] args) {
        this.args = args;
    }

    private boolean isTest() {
        return args.length > 0 && args[0].equals("configuration_test");
    }

    protected InputBoundary getInputBoundary() {
        return (isTest()) ? TestSystemInputOuput.instance() : new SystemInput();
    }

    protected OutputBoundary getOutputBoundary() {
        return (isTest()) ? TestSystemInputOuput.instance() : new SystemOutput();
    }

    public final GooseGameApp buildApplication() {
        InputBoundary inputBoundary = getInputBoundary();
        OutputBoundary outputBoundary = getOutputBoundary();
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(new Players(), outputBoundary);

        return new GooseGameApp(inputBoundary, addPlayerUseCase);
    }
}
