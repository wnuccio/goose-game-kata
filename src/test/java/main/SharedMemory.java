package main;

import boundary.application.InputOutput;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

class SharedMemory implements InputOutput {
    private BlockingQueue<String> inputQueue = new LinkedBlockingDeque<>();
    private BlockingQueue<String> outputQueue = new LinkedBlockingDeque<>();

    public SharedMemory() { }

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
