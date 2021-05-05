public class GooseGameAppBuilder {
    private boolean isTest(String[] args) {
        return args.length > 0 && args[0].equals("test");
    }

    private InputBoundary getInputBoundary(String[] args) {
        return (isTest(args)) ? TestSystemInputOuput.instance() : new SystemInput();
    }

    private OutputBoundary getOutputBoundary(String[] args) {
        return (isTest(args)) ? TestSystemInputOuput.instance() : new SystemOutput();
    }

    public GooseGameApp buildApplication(String[] args) {
        InputBoundary inputBoundary = getInputBoundary(args);
        OutputBoundary outputBoundary = getOutputBoundary(args);

        return new GooseGameApp(inputBoundary, outputBoundary);
    }
}
