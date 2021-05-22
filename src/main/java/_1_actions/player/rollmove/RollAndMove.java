package _1_actions.player.rollmove;

import _1_actions.player.move.MovePlayer;
import _2_domain.movement.DiceRoller;
import _2_domain.movement.MoveCommand;

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
