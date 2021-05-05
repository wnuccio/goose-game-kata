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

        return new GooseGameApp(inputBoundary, outputBoundary);
    }
}
