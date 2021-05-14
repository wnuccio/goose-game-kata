package main;

import boundary.application.GooseGameApp;
import main.test.GooseGameAppBuilderForTest;

public class Main {
    static GooseGameAppBuilderForTest injectedBuilder;

    public static void main(String[] args) {
        GooseGameAppBuilder appBuilder = selectApplicationBuilder(args);
        GooseGameApp app = appBuilder.buildApplication();
        app.run();
    }

    private static GooseGameAppBuilder selectApplicationBuilder(String[] args) {
        return injectedBuilder != null ? injectedBuilder : new GooseGameAppBuilder();
    }
}
