import add_player.AddPlayerUseCase;
import configuration.GooseGameApp;
import configuration.GooseGameAppBuilder;
import configuration.InputBoundary;

public class Main {

    public static void main(String[] args) {
        GooseGameApp app = new GooseGameAppBuilder(args).buildApplication();
        app.run();
    }
}
