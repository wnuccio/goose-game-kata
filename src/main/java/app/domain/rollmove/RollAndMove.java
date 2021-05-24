package app.domain.rollmove;

import app.domain.movement.MoveCommand;
import app.domain.movement.MovePlayer;

public class RollAndMove {
    private final DiceRoller diceRoller;
    private final MovePlayer movePlayer;

    public RollAndMove(DiceRoller diceRoller, MovePlayer movePlayer) {
        this.diceRoller = diceRoller;
        this.movePlayer = movePlayer;
    }

    public void acceptCommand(String player) {
        MoveCommand commandWithDice = new MoveCommand(player, diceRoller.roll());
        movePlayer.acceptCommand(commandWithDice);
    }
}
