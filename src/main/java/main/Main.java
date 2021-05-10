package main;

import boundary.application.GooseGameApp;
import main.test.GooseGameAppBuilderForTest;

import java.util.Arrays;

public class Main {
    public static final String[] ARGS_FOR_TEST = {"--test"};

    public static void main(String[] args) {
        GooseGameAppBuilder appBuilder = selectApplicationBuilder(args);
        GooseGameApp app = appBuilder.buildApplication();
        app.run();
    }

    private static GooseGameAppBuilder selectApplicationBuilder(String[] args) {
        return isApplicationToTest(args) ?
                new GooseGameAppBuilderForTest() :
                new GooseGameAppBuilder();
    }

    private static boolean isApplicationToTest(String[] args) {
        return Arrays.equals(ARGS_FOR_TEST, args);
    }
}
