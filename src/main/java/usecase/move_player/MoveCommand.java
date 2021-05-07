package usecase.move_player;

public class MoveCommand {
    private String player;
    private Integer firstDice;
    private Integer secondDice;

    public MoveCommand(String player) {
        this.player = player;
    }

    public void dice(int firstDice, int secondDice) {
        this.firstDice = firstDice;
        this.secondDice = secondDice;
    }

    public String playerName() {
        return player;
    }

    public int firstDice() {
        return firstDice;
    }

    public int secondDice() {
        return secondDice;
    }

    public boolean hasNoDice() {
        return firstDice == null;
    }

    public void setDice(Dice dice) {
        firstDice = dice.first();
        secondDice = dice.second();
    }
}
