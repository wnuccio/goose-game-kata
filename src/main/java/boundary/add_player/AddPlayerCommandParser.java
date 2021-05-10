package boundary.add_player;

import boundary.CommandLineParser;

public class AddPlayerCommandParser {
    public String playerName(String commandLine) {
        return new CommandLineParser(commandLine).token(2);
    }
}
