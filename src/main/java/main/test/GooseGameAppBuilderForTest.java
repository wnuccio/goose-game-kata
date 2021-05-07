package main.test;

import console.OutputBoundary;
import main.GooseGameAppBuilder;
import main.InputBoundary;
import usecase.move_player.Dice;

public class GooseGameAppBuilderForTest extends GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        return TestSystemInputOuput.instance();
    }

    protected OutputBoundary getOutputBoundary() {
        return TestSystemInputOuput.instance();
    }

    @Override
    protected Dice dice() {
        return DiceForTest.instance();
    }
}
