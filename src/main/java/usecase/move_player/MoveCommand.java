package usecase.move_player;

import domain.Dice;

public class MoveCommand {
    private String player;
    private Dice dice;

    public MoveCommand(String player, Dice dice) {
        this.player = player;
        this.dice = dice;
    }

    public String player() {
        return player;
    }

    public Dice dice() {
        return dice;
    }

    public int diceTotal() {
        return dice.total();
    }
}
