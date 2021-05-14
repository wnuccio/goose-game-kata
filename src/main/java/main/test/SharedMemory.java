package main.test;

import boundary.application.InputOutput;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

class SharedMemory implements InputOutput {
    private BlockingQueue<String> inputQueue = new LinkedBlockingDeque<>();
    private BlockingQueue<String> outputQueue = new LinkedBlockingDeque<>();

    private static SharedMemory instance = new SharedMemory();
    public static SharedMemory instance() {
        return instance;
    }
    private SharedMemory() { }

    void writeInputByTest(String inputLine) {
        execute(() -> inputQueue.offer(inputLine));
    }

    @Override
    public String readInputLine() {
        return execute(() -> inputQueue.take());
    }

    @Override
    public void writeOutputLine(String outputLine) {
        execute(() -> outputQueue.add(outputLine));
        System.out.println(outputLine);
    }

    String readOutputByTest() {
        return execute(() -> outputQueue.poll(5, MILLISECONDS));
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
