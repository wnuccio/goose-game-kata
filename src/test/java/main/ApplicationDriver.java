package main;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ApplicationDriver {
    private SharedMemory inputOuput;
    private DiceRollerStub diceRollerStub;

    public ApplicationDriver(SharedMemory inputOuput, DiceRollerStub diceRollerStub) {
        this.inputOuput = inputOuput;
        this.diceRollerStub = diceRollerStub;
    }

    public String acceptInput(String inputString) {
        inputOuput.writeInputByTest(inputString);
        return inputOuput.readOutputByTest(5, MILLISECONDS);
    }

    public DiceRollerStub diceRollerStub() {
        return diceRollerStub;
    }
}
