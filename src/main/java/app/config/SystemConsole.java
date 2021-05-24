package app.config;

import java.util.Scanner;

public class SystemConsole implements InputOutput {
    @Override
    public void writeOutputLine(String string) {
        System.out.println(string);
    }

    @Override
    public String readInputLine() {
        return new Scanner(System.in).nextLine();
    }
}
