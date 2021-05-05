public class GooseGameApp {

    private InputBoundary input;
    private OutputBoundary output;

    public GooseGameApp(InputBoundary input, OutputBoundary output) {
        this.input = input;
        this.output = output;
    }

    public static void main(String[] args) {
        GooseGameApp app = new GooseGameAppBuilder(args).buildApplication();
        app.run();
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
