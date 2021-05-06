package main;

import main.test.GooseGameAppBuilderForTest;

import java.util.Arrays;

public class Main {
    public static final String[] ARGS_FOR_TEST = {"main/test"};

    public static void main(String[] args) {
        GooseGameApp app;

        try {
            GooseGameAppBuilder appBuilder = isApplicationToTest(args) ?
                new GooseGameAppBuilderForTest() :
                new GooseGameAppBuilder();

            app = appBuilder.buildApplication();
            app.run();

        } catch (Exception e) {
            e.printStackTrace();
//            app.run();
        }
    }

    private static boolean isApplicationToTest(String[] args) {
        return Arrays.equals(ARGS_FOR_TEST, args);
    }
}
