import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SystemInput implements InputBoundary {
    Scanner inputScanner = new Scanner(System.in);

    @Override
    public String readInputLine() {
        return inputScanner.nextLine();
    }
}


