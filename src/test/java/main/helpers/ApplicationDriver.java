package main.helpers;

import main.test.TestSystemInputOuput;

public class ApplicationDriver {
    private TestSystemInputOuput inputOuput = TestSystemInputOuput.instance();

    public String acceptInput(String inputString) {
        inputOuput.writeInputByTest(inputString);
        waitSomeSeconds();
        return inputOuput.readOutputByTest();
    }

    private void waitSomeSeconds() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
