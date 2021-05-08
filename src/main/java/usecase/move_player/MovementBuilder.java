package usecase.move_player;

public class MovementBuilder {

    private final SimpleMovement movement;

    public MovementBuilder(String player) {
        movement = new SimpleMovement(player);
    }

    public MovementBuilder from(int fromPosition) {
        movement.fromPosition = fromPosition;
        return this;
    }

    public MovementBuilder givenRoll(Dice dice) {
        movement.dice = dice;
        return this;
    }

    public SimpleMovement end() {
        return movement;
    }
}