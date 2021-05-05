package configuration.test;

import usecase.OutputBoundary;
import configuration.InputBoundary;

import java.util.ArrayList;
import java.util.List;

public class TestSystemInputOuput implements InputBoundary, OutputBoundary {
    private static TestSystemInputOuput instance = new TestSystemInputOuput();

    private String inputString = null;
    private List<String> outputStrings = new ArrayList<>();

    private TestSystemInputOuput() {
    }

    public static TestSystemInputOuput instance() {
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

    synchronized public void writeInput(String inputString) {
        this.inputString = inputString;
    }

    synchronized public String readOutput() {
        if (outputStrings == null || outputStrings.isEmpty()) return "";

        String result = outputStrings.get(0);
        outputStrings.remove(0);
        return result;
    }
}
