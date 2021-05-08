package usecase.move_player;

import java.util.Optional;

public class MoveCommand {
    private String player;
    private Dice dice;

    public MoveCommand(String player) {
        this(player, null);
    }

    public MoveCommand(String player, Dice dice) {
        this.player = player;
        this.dice = dice;
    }

    public String player() {
        return player;
    }

    public Optional<Dice> dice() {
        return Optional.ofNullable(dice);
    }
}
