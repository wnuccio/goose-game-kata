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

    public void present(MovementPresenter movementPresenter) {
        movement.present(movementPresenter);
    }

    public Movement movement() {
        return this.movement;
    }
}
