package boundary;

import usecase.CommandLineParser;

public class AddPlayerCommandParser extends CommandLineParser {
    public AddPlayerCommandParser(String commandLine) {
        super(commandLine);
    }

    public String playerName() {
        return token(2);
    }
}
