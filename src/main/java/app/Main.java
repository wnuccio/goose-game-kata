package app;

import app.config.GameConfiguration;
import app.config.GooseGameAppBuilder;
import app.console.GameController;

public class Main {
    public static GooseGameAppBuilder injectedBuilder;

    public static void main(String[] args) {
        GooseGameAppBuilder appBuilder = selectApplicationBuilder(args);
        GameController gameController = new GameConfiguration(appBuilder).buildApplication();
        gameController.run();
    }

    private static GooseGameAppBuilder selectApplicationBuilder(String[] args) {
        return injectedBuilder != null ? injectedBuilder : new GooseGameAppBuilder();
    }
}
