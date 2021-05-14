package main;

import boundary.application.InputOutput;
import domain.DiceRoller;

public class GooseGameAppBuilderForTest extends GooseGameAppBuilder {

    private SharedMemory sharedMemory;
    private DiceRoller diceRollerStub;

    public GooseGameAppBuilderForTest(SharedMemory sharedMemory, DiceRoller diceRollerStub) {
        this.sharedMemory = sharedMemory;
        this.diceRollerStub = diceRollerStub;
    }

    @Override
    protected InputOutput getInputOutput() {
        return sharedMemory;
    }

    @Override
    protected DiceRoller diceRoller() {
        return diceRollerStub;
    }
}
