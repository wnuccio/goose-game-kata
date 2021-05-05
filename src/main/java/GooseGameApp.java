import add_player.AddPlayerUseCase;
import add_player.OutputBoundary;

public class GooseGameApp {

    private InputBoundary input;
    private AddPlayerUseCase addPlayerUseCase;

    public GooseGameApp(InputBoundary input, AddPlayerUseCase addPlayerUseCase) {
        this.input = input;
        this.addPlayerUseCase = addPlayerUseCase;
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
            addPlayerUseCase.acceptCommand(line);
        }
    }

}
