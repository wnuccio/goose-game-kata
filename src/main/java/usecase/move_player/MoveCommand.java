package usecase.move_player;

import usecase.AbstractCommand;

public class MoveCommand extends AbstractCommand {

    public MoveCommand(String commandLine) {
        super(commandLine);
    }

    public String playerName() { return token(1); }

    public int firstDice() {
        return numberAt(2);
    }

    public int secondDice() {
        return numberAt(3);
    }

    public boolean hasNoDice() {
        return super.tokenNumber() == 2;
    }
}
