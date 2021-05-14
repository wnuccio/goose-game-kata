package main.test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ApplicationDriver {
    private SharedMemory inputOuput = SharedMemory.instance();

    public String acceptInput(String inputString) {
        inputOuput.writeInputByTest(inputString);
        return inputOuput.readOutputByTest(5, MILLISECONDS);
    }
}
