package main;

import boundary.application.GooseGameApp;
import main.test.GooseGameAppBuilderForTest;

import java.util.Arrays;

public class Main {
    public static final String[] ARGS_FOR_TEST = {"main/test"};

    public static void main(String[] args) {
        GooseGameAppBuilder appBuilder = isApplicationToTest(args) ?
                new GooseGameAppBuilderForTest() :
                new GooseGameAppBuilder();

        GooseGameApp app = appBuilder.buildApplication();
        app.run();
    }

    private static boolean isApplicationToTest(String[] args) {
        return Arrays.equals(ARGS_FOR_TEST, args);
    }
}
