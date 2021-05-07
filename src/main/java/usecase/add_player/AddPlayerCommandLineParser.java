package usecase.add_player;

import usecase.CommandLineParser;

public class AddPlayerCommandLineParser extends CommandLineParser {
    public AddPlayerCommandLineParser(String commandLine) {
        super(commandLine);
    }

    public String playerName() {
        return token(2);
    }
}
