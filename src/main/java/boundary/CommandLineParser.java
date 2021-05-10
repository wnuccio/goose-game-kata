package boundary;

import static java.lang.Integer.parseInt;

public class CommandLineParser {
    private String commandLine;

    public CommandLineParser(String commandLine) {
        this.commandLine = commandLine;
    }

    public String token(int i) {
        return tokens()[i];
    }

    public int digitAtToken(int i) {
        String character = token(i).substring(0, 1);
        return parseInt(character);
    }

    public int tokenNumber() {
        return tokens().length;
    }

    private String[] tokens() {
        return commandLine.split(" ");
    }

    public boolean lineStartsWith(String firstToken) {
        if (tokenNumber() == 0) return false;

        return token(0).equals(firstToken);
    }
}
