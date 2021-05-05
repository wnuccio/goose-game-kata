import configuration_test.TestSystemInputOuput;

public class ApplicationRunner {
    private TestSystemInputOuput inputOuput = TestSystemInputOuput.instance();
    private Thread thread;

    public void runApplication() {
        thread = new Thread(() -> Main.main(Main.ARGS_FOR_TEST));
        thread.setDaemon(true);
        thread.start();
    }

    public void stopApplication() {
        inputOuput.writeInput("exit");
    }

    public void acceptInput(String inputString) {
        inputOuput.writeInput(inputString);
    }

    public String waitForOutput() {
        waitSomeSeconds();
        return inputOuput.readOutput();
    }

    private void waitSomeSeconds() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
