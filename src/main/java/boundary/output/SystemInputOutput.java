package boundary.output;

import boundary.application.InputBoundary;

import java.util.Scanner;

public class SystemInputOutput implements InputBoundary, OutputBoundary {
    @Override
    public void writeOutputLine(String string) {
        System.out.println(string);
    }

    @Override
    public String readInputLine() {
        return new Scanner(System.in).nextLine();
    }
}
