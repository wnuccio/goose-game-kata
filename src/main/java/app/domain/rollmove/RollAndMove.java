package app.domain.rollmove;

import app.domain.movement.FindPlayer;
import app.domain.movement.MoveCommand;

public class RollAndMove {
    private final DiceRoller diceRoller;
    private final FindPlayer findPlayer;

    public RollAndMove(DiceRoller diceRoller, FindPlayer findPlayer) {
        this.diceRoller = diceRoller;
        this.findPlayer = findPlayer;
    }

    public void acceptCommand(String player) {
        MoveCommand commandWithDice = new MoveCommand(player, diceRoller.roll());
        findPlayer.acceptCommand(commandWithDice);
    }
}
