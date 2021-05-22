package _0_config;

import _0_config.config.GameConfiguration;
import _0_config.config.GooseGameAppBuilder;
import _1_actions.controller.Game;

public class Main {
    public static GooseGameAppBuilder injectedBuilder;

    public static void main(String[] args) {
        GooseGameAppBuilder appBuilder = selectApplicationBuilder(args);
        Game game = new GameConfiguration(appBuilder).buildApplication();
        game.run();
    }

    private static GooseGameAppBuilder selectApplicationBuilder(String[] args) {
        return injectedBuilder != null ? injectedBuilder : new GooseGameAppBuilder();
    }
}
