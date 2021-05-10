package boundary;

import usecase.CommandLineParser;

public class AddPlayerCommandParser {
    public String playerName(String commandLine) {
        return new CommandLineParser(commandLine).token(2);
    }
}
