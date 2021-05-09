package main;

import main.test.TestSystemInputOuput;

public class ApplicationDriver {
    private TestSystemInputOuput inputOuput = TestSystemInputOuput.instance();

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
