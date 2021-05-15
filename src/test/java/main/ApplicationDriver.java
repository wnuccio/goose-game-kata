package main;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ApplicationDriver {
    private SharedMemory inputOuput;

    public ApplicationDriver(SharedMemory inputOuput) {
        this.inputOuput = inputOuput;
    }

    public String acceptInput(String inputString) {
        inputOuput.writeInputByTest(inputString);
        return inputOuput.readOutputByTest(15, MILLISECONDS);
    }

}
