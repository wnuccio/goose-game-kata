public class GooseGameApp {

    private InputBoundary input;
    private OutputBoundary output;

    public GooseGameApp(InputBoundary input, OutputBoundary output) {
        this.input = input;
        this.output = output;
    }

    public static void main(String[] args) {
        GooseGameApp app = buildApplication(args);
        app.run();
    }

    private static GooseGameApp buildApplication(String[] args) {
        InputBoundary inputBoundary = getInputBoundary(args);
        OutputBoundary outputBoundary = getOutputBoundary(args);

        return new GooseGameApp(inputBoundary, outputBoundary);
    }

    private static OutputBoundary getOutputBoundary(String[] args) {
        return (isTest(args)) ? TestSystemInputOuput.instance() : new SystemOutput();
    }

    private static InputBoundary getInputBoundary(String[] args) {
        return (isTest(args)) ? TestSystemInputOuput.instance() : new SystemInput();
    }

    private static boolean isTest(String[] args) {
        return args.length > 0 && args[0].equals("test");
    }

    private void run() {
        System.out.println("Application runned");
        while(true) {
            String line = input.readInputLine();
            if (line == null) continue;
            if ("exit".equals(line)) {
                System.out.println("Application stopped");
                break;
            }
            output.writeOutputLine("Wrong output: " + line);
        }
    }

}
