package main.test;

import boundary.application.InputOutput;
import domain.DiceRoller;
import main.GooseGameAppBuilder;

public class GooseGameAppBuilderForTest extends GooseGameAppBuilder {

    @Override
    protected InputOutput getInputOutput() {
        return SharedMemory.instance();
    }

    @Override
    protected DiceRoller diceRoller() {
        return DiceRollerStub.instance();
    }
}
