package main.test;

import boundary.application.InputBoundary;
import boundary.output.OutputBoundary;

class SharedMemory implements InputBoundary, OutputBoundary {
    private String inputString = null;
    private String outputString = null;

    private static SharedMemory instance = new SharedMemory();
    public static SharedMemory instance() {
        return instance;
    }
    private SharedMemory() { }

    synchronized void writeInputByTest(String inputString) {
        this.inputString = inputString;
    }

    @Override
    synchronized public String readInputLine() {
        String result = inputString;
        inputString = null;
        return result;
    }

    @Override
    synchronized public void writeOutputLine(String string) {
        outputString = string;
        System.out.println(string);
    }

    synchronized String readOutputByTest() {
        String result = outputString;
        outputString = null;
        return result;

    }
}
