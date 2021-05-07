package usecase.add_player;

import usecase.AbstractCommand;

public class AddPlayerCommand extends AbstractCommand {
    public AddPlayerCommand(String commandLine) {
        super(commandLine);
    }

    public String playerName() {
        return token(2);
    }
}
