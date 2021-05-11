package usecase.move_player;

import domain.DiceRoller;

public class RollAndMove {
    private DiceRoller diceRoller;
    private MovePlayer movePlayer;

    public RollAndMove(DiceRoller diceRoller, MovePlayer movePlayer) {
        this.diceRoller = diceRoller;
        this.movePlayer = movePlayer;
    }

    public void acceptCommand(String player) {
        MoveCommand commandWithDice = new MoveCommand(player, diceRoller.roll());
        movePlayer.acceptCommand(commandWithDice);
    }
}
