package usecase;

import static java.lang.Integer.parseInt;

public abstract class AbstractCommand {
    private String commandLine;

    protected AbstractCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    protected String token(int i) {
        String[] tokens = commandLine.split(" ");
        return tokens[i];
    }

    protected int numberAt(int i) {
        String character = token(i).substring(0, 1);
        return parseInt( character);
    }
}
