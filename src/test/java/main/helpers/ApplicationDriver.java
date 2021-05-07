package main.helpers;

import main.test.TestSystemInputOuput;

import static java.lang.String.format;

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

    public String movePlayer(String player, int firstDice, int secondDice) {
        return acceptInput(format("move %s %d, %d", player, firstDice, secondDice));
    }

}
