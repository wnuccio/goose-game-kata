package usecase.move_player;

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

    public void setDiceIfAbsent(Dice dice) {
        if (this.dice == null) {
            this.dice = dice.roll();
        }
    }

    public Dice dice() {
        return dice;
    }
}
