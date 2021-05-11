package usecase.move_player;

import boundary.output.MovementPresenter;

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

    public void present(MovementPresenter movementPresenter) {
        movement.present(movementPresenter);
    }
}
