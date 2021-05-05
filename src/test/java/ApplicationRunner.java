import org.junit.jupiter.api.Test;

public class ApplicationRunner {

    private TestSystemInputOuput inputOuput = TestSystemInputOuput.instance();
    private Thread thread;

    public void runApplication() {
        thread = new Thread(() -> GooseGameApp.main(new String[]{"test"}));
        thread.setDaemon(true);
        thread.start();
    }

    public void stopApplication() {
        inputOuput.writeInput("exit");
    }

    private void waitSomeSeconds() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void acceptInput(String inputString) {
        inputOuput.writeInput(inputString);
    }

    public String waitForOutput() {
        waitSomeSeconds();
        return inputOuput.readOutput();
    }
}
