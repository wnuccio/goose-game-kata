package goosegame;

import goosegame.application.GooseGameApp;
import goosegame.config.AppConfiguration;
import goosegame.config.GooseGameAppBuilder;

public class Main {
    public static GooseGameAppBuilder injectedBuilder;

    public static void main(String[] args) {
        GooseGameAppBuilder appBuilder = selectApplicationBuilder(args);
        GooseGameApp app = new AppConfiguration(appBuilder).buildApplication();
        app.run();
    }

    private static GooseGameAppBuilder selectApplicationBuilder(String[] args) {
        return injectedBuilder != null ? injectedBuilder : new GooseGameAppBuilder();
    }
}
