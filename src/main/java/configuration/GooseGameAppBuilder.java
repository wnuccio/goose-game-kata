package configuration;

import add_player.AddPlayerUseCase;
import add_player.OutputBoundary;
import add_player.Players;

public class GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        return new SystemInput();
    }

    protected OutputBoundary getOutputBoundary() {
        return new SystemOutput();
    }

    public final GooseGameApp buildApplication() {
        InputBoundary inputBoundary = getInputBoundary();
        OutputBoundary outputBoundary = getOutputBoundary();
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(new Players(), outputBoundary);

        return new GooseGameApp(inputBoundary, addPlayerUseCase);
    }
}
