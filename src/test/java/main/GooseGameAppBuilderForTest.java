package main;

import boundary.application.InputOutput;
import domain.DiceRoller;

public class GooseGameAppBuilderForTest extends GooseGameAppBuilder {

    private SharedMemory sharedMemory;

    public GooseGameAppBuilderForTest(SharedMemory sharedMemory) {
        this.sharedMemory = sharedMemory;
    }

    @Override
    protected InputOutput getInputOutput() {
        return sharedMemory;
    }

    @Override
    protected DiceRoller diceRoller() {
        return DiceRollerStub.instance();
    }
}
