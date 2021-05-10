package main.test;

import boundary.application.InputBoundary;
import boundary.output.OutputBoundary;
import domain.DiceRoller;
import main.GooseGameAppBuilder;

public class GooseGameAppBuilderForTest extends GooseGameAppBuilder {

    @Override
    protected InputBoundary getInputBoundary() {
        return SharedMemory.instance();
    }

    @Override
    protected OutputBoundary getOutputBoundary() {
        return SharedMemory.instance();
    }

    @Override
    protected DiceRoller diceRoller() {
        return DiceRollerStub.instance();
    }
}
