package main.test;

import boundary.OutputBoundary;
import main.InputBoundary;

import java.util.ArrayList;
import java.util.List;

public class SharedMemory implements InputBoundary, OutputBoundary {
    private static SharedMemory instance = new SharedMemory();

    private String inputString = null;
    private List<String> outputStrings = new ArrayList<>();

    private SharedMemory() { }

    public static SharedMemory instance() {
        return instance;
    }

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

    synchronized public void writeInputByTest(String inputString) {
        this.inputString = inputString;
    }

    synchronized public String readOutputByTest() {
        if (outputStrings == null || outputStrings.isEmpty()) return "";

        String result = outputStrings.get(0);
        outputStrings.remove(0);
        return result;
    }
}
