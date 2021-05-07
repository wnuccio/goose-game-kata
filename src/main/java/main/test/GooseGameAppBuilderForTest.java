package main.test;

import console.OutputBoundary;
import main.GooseGameAppBuilder;
import main.InputBoundary;

public class GooseGameAppBuilderForTest extends GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        return TestSystemInputOuput.instance();
    }

    protected OutputBoundary getOutputBoundary() {
        return TestSystemInputOuput.instance();
    }
}
