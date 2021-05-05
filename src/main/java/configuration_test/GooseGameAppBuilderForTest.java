package configuration_test;

import add_player.OutputBoundary;
import configuration.GooseGameAppBuilder;
import configuration.InputBoundary;

public class GooseGameAppBuilderForTest extends GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        return TestSystemInputOuput.instance();
    }

    protected OutputBoundary getOutputBoundary() {
        return TestSystemInputOuput.instance();
    }
}
