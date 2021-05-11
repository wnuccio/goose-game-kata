package usecase.move_player;

public class MovementView {
    private Movement movement;
    private String player;
    private int firstDice;
    private int secondDice;

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

    public int startPosition() {
        return movement.startPosition();
    }

    public int finalPosition() {
        return movement.finalPosition();
    }

    public boolean isVictory() {
        return movement.isVictory();
    }

    public MovementType type() {
        return movement.type();
    }

    public MovementView previousMovement() {
        return new MovementView(movement.previousMovement(), player, firstDice, secondDice);
    }

    public boolean endsOnGoose() {
        return movement.endsOnGoose();
    }
}
