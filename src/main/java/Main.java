import configuration.GooseGameApp;
import configuration.GooseGameAppBuilder;
import configuration_test.GooseGameAppBuilderForTest;

import java.util.Arrays;

public class Main {
    public static final String[] ARGS_FOR_TEST = {"configuration_test"};

    public static void main(String[] args) {
        GooseGameAppBuilder appBuilder = isApplicationToTest(args) ?
                new GooseGameAppBuilderForTest() :
                new GooseGameAppBuilder();

        GooseGameApp app = appBuilder.buildApplication();

        try {
            app.run();

        } catch (Exception e) {
            e.printStackTrace();
            app.run();
        }
    }

    private static boolean isApplicationToTest(String[] args) {
        return Arrays.equals(ARGS_FOR_TEST, args);
    }
}
