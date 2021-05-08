package usecase.move_player;

public class MovementBuilder {

    private final Movement movement;

    public MovementBuilder(String player) {
        movement = new Movement(player);
    }

    public MovementBuilder givenRoll(Dice dice) {
        movement.dice = dice;
        return this;
    }

    public MovementBuilder from(int fromPosition) {
        movement.fromPosition = fromPosition;
        return this;
    }

    public MovementBuilder to(int toPosition) {
//        movement.toPosition = toPosition;
        return this;
    }

    public MovementBuilder isBouncing(boolean isBouncing) {
//        movement.bouncing = isBouncing;
        return this;
    }

    public Movement end() {
        return movement;
    }
}