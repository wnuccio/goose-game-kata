package boundary;

public class AddPlayerCommandParser {
    public String playerName(String commandLine) {
        return new CommandLineParser(commandLine).token(2);
    }
}
