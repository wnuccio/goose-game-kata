package _0_config;

import _0_config.config.GameConfiguration;
import _0_config.config.GooseGameAppBuilder;
import _0_config.controller.GameController;

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
