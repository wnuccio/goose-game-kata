package app.config;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ApplicationDriver {
    private final SharedMemory inputOuput;

    public ApplicationDriver(SharedMemory inputOuput) {
        this.inputOuput = inputOuput;
    }

    public void acceptInputNoOutput(String inputString) {
        inputOuput.writeInputByTest(inputString);
    }

    public String acceptInput(String inputString) {
        inputOuput.writeInputByTest(inputString);

        int timeout = 15;
        TimeUnit timeUnit = MILLISECONDS;

        String output = inputOuput.readOutputByTest(timeout, timeUnit);

        checkNotNullOutput(inputString, timeout, timeUnit, output);

        return output;
    }

    private void checkNotNullOutput(String inputString, int timeout, TimeUnit timeUnit, String output) {
        if (output == null) throw new IllegalStateException(
                format("No output returned for input %s within %s %s", inputString, timeout, timeUnit));
    }
}
