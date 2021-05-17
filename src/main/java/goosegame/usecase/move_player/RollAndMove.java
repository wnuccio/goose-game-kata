package goosegame.usecase.move_player;

import goosegame.domain.DiceRoller;

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
