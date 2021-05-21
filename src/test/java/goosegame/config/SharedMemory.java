package goosegame.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class SharedMemory implements InputOutput {
    private final BlockingQueue<String> inputQueue = new LinkedBlockingDeque<>();
    private final BlockingQueue<String> outputQueue = new LinkedBlockingDeque<>();

    public SharedMemory() { }

    void writeInputByTest(String inputLine) {
        execute(() -> inputQueue.put(inputLine));
    }

    @Override
    public String readInputLine() {
        String input;
        do {
            input = execute(() -> inputQueue.take());
        } while(input == null);

        System.out.println("> " + input);
        return input;
    }

    @Override
    public void writeOutputLine(String outputLine) {
        execute(() -> outputQueue.put(outputLine));
        System.out.println(outputLine);
    }

    String readOutputByTest(int timeout, TimeUnit timeUnit) {
        return execute(() -> outputQueue.poll(timeout, timeUnit));
    }

    private interface QueueAction { void doCommand() throws InterruptedException; }
    private interface QueueQuery { String doQuery() throws InterruptedException; }
    private void execute(QueueAction command) {
        try {
            command.doCommand();

        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
    private String execute(QueueQuery query) {
        try {
            return query.doQuery();

        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
