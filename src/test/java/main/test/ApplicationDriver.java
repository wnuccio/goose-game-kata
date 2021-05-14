package main.test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ApplicationDriver {
    private SharedMemory inputOuput = SharedMemory.instance();

    public String acceptInput(String inputString) {
        inputOuput.writeInputByTest(inputString);
        return inputOuput.readOutputByTest(5, MILLISECONDS);
    }

    public void waitAbit() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
