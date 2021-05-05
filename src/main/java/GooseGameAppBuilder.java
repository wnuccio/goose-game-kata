import add_player.AddPlayerUseCase;
import add_player.OutputBoundary;

public class GooseGameAppBuilder {
    private String[] args;

    public GooseGameAppBuilder(String[] args) {
        this.args = args;
    }

    private boolean isTest() {
        return args.length > 0 && args[0].equals("test");
    }

    private InputBoundary getInputBoundary() {
        return (isTest()) ? TestSystemInputOuput.instance() : new SystemInput();
    }

    private OutputBoundary getOutputBoundary() {
        return (isTest()) ? TestSystemInputOuput.instance() : new SystemOutput();
    }

    public GooseGameApp buildApplication() {
        InputBoundary inputBoundary = getInputBoundary();
        OutputBoundary outputBoundary = getOutputBoundary();
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(outputBoundary);

        return new GooseGameApp(inputBoundary, addPlayerUseCase);
    }
}
