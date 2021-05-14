package main;

import boundary.application.InputOutput;
import domain.DiceRoller;

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
