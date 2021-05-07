package usecase;

import static java.lang.Integer.parseInt;

public class CommandLineParser {
    private String commandLine;

    public CommandLineParser(String commandLine) {
        this.commandLine = commandLine;
    }

    public String token(int i) {
        String[] tokens = commandLine.split(" ");
        return tokens[i];
    }

    public int numberAt(int i) {
        String character = token(i).substring(0, 1);
        return parseInt( character);
    }

    public int tokenNumber() {
        return tokens().length;
    }

    private String[] tokens() {
        return commandLine.split(" ");
    }
}
