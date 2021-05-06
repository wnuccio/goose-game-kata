package usecase;

public class AddPlayerCommand {
    private String commandLine;

    public AddPlayerCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    public String playerName() {
        return token(2);
    }

    private String token(int i) {
        String[] tokens = commandLine.split(" ");
        return tokens[i];
    }
}
