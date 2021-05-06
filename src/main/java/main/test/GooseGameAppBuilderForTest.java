package main.test;

import main.GooseGameAppBuilder;
import main.InputBoundary;
import usecase.OutputBoundary;

public class GooseGameAppBuilderForTest extends GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        return TestSystemInputOuput.instance();
    }

    protected OutputBoundary getOutputBoundary() {
        return TestSystemInputOuput.instance();
    }
}
