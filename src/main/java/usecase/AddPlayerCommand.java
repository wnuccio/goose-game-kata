package usecase;

public class AddPlayerCommand extends AbstractCommand {
    public AddPlayerCommand(String commandLine) {
        super(commandLine);
    }

    public String playerName() {
        return token(2);
    }
}
