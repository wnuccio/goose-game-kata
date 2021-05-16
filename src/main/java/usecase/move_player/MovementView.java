package usecase.move_player;


public class MovementView {
    private final Movement movement;
    private final String player;
    private final int firstDice;
    private final int secondDice;

    public MovementView(Movement movement, String player, int firstDice, int secondDice) {
        this.movement = movement;
        this.player = player;
        this.firstDice = firstDice;
        this.secondDice = secondDice;
    }

    public String player() {
        return player;
    }

    public int firstDice() {
        return firstDice;
    }

    public int secondDice() {
        return secondDice;
    }

    public Movement movement() {
        return this.movement;
    }
}
