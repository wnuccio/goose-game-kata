package goosegame.config;

import goosegame.boundary.application.InputOutput;

import java.util.Scanner;

public class SystemInputOutput implements InputOutput {
    @Override
    public void writeOutputLine(String string) {
        System.out.println(string);
    }

    @Override
    public String readInputLine() {
        return new Scanner(System.in).nextLine();
    }
}
