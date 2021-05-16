package usecase.move_player;

import domain.Dice;

public class MovementBuilder {

    private final SimpleMovement movement;

    public MovementBuilder() {
        movement = new SimpleMovement();
    }

    public MovementBuilder from(int startPosition) {
        movement.startPosition = startPosition;
        return this;
    }

    public MovementBuilder to(int finalPosition) {
        movement.finalPosition = finalPosition;
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