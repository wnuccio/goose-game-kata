package app;

import app.config.AppConfiguration;
import app.config.GooseGameAppBuilder;
import app.domain.game.Game;

public class Main {
    public static GooseGameAppBuilder injectedBuilder;

    public static void main(String[] args) {
        GooseGameAppBuilder appBuilder = selectApplicationBuilder(args);
        Game app = new AppConfiguration(appBuilder).buildApplication();
        app.run();
    }

    private static GooseGameAppBuilder selectApplicationBuilder(String[] args) {
        return injectedBuilder != null ? injectedBuilder : new GooseGameAppBuilder();
    }
}
