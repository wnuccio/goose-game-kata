package main;

import main.test.TestSystemInputOuput;

public class ApplicationRunner {
    private TestSystemInputOuput inputOuput = TestSystemInputOuput.instance();
    private Thread thread;

    public void runApplication() {
        thread = new Thread(() -> Main.main(Main.ARGS_FOR_TEST));
        thread.setDaemon(true);
        thread.start();
    }

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
