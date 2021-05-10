package main.test;

import boundary.console.OutputBoundary;
import main.InputBoundary;

import java.util.ArrayList;
import java.util.List;

class SharedMemory implements InputBoundary, OutputBoundary {
    private static SharedMemory instance = new SharedMemory();

    private String inputString = null;
    private List<String> outputStrings = new ArrayList<>();

    public static SharedMemory instance() {
        return instance;
    }

    private SharedMemory() { }

    @Override
    synchronized public String readInputLine() {
        String result = inputString;
        inputString = null;
        return result;
    }

    @Override
    synchronized public void writeOutputLine(String string) {
        outputStrings.add(string);
    }

    synchronized void writeInputByTest(String inputString) {
        this.inputString = inputString;
    }

    synchronized String readOutputByTest() {
        if (outputStrings == null || outputStrings.isEmpty()) return "";

        String result = outputStrings.get(0);
        outputStrings.remove(0);
        return result;
    }
}
