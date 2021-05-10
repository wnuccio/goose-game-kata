package main.test;

public class ApplicationDriver {
    private SharedMemory inputOuput = SharedMemory.instance();

    public String acceptInput(String inputString) {
        inputOuput.writeInputByTest(inputString);
        waitAbit();
        return inputOuput.readOutputByTest();
    }

    private void waitAbit() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
