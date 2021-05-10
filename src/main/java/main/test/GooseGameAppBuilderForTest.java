package main.test;

import boundary.console.OutputBoundary;
import domain.DiceRoller;
import main.GooseGameAppBuilder;
import main.InputBoundary;

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
