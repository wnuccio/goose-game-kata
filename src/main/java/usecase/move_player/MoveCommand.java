package usecase.move_player;

public class MoveCommand {
    private String player;
    private Dice dice;

    public MoveCommand(String player) {
        this.player = player;
    }

    public String player() {
        return player;
    }

    public boolean hasNoDice() {
        return dice == null;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Dice dice() {
        return dice;
    }
}
