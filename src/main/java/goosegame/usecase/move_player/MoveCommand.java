package goosegame.usecase.move_player;

import goosegame.domain.Dice;

public class MoveCommand {
    private final String player;
    private final Dice dice;

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
