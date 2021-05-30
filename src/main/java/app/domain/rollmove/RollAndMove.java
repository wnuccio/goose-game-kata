package app.domain.rollmove;

import app.domain.movement.FindPlayer;
import app.domain.player.Dice;

public class RollAndMove {
    private final DiceRoller diceRoller;
    private final FindPlayer findPlayer;

    public RollAndMove(DiceRoller diceRoller, FindPlayer findPlayer) {
        this.diceRoller = diceRoller;
        this.findPlayer = findPlayer;
    }

    public void acceptCommand(String playerName) {
        Dice dice = diceRoller.roll();
        findPlayer.acceptCommand(playerName, dice);
    }
}
