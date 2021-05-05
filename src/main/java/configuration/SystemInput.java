package configuration;

import java.util.Scanner;

public class SystemInput implements InputBoundary {
    Scanner inputScanner = new Scanner(System.in);

    @Override
    public String readInputLine() {
        return inputScanner.nextLine();
    }
}


