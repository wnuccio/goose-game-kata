package goosegame.usecase.move_player;

import goosegame.domain.Dice;
import goosegame.domain.Position;

public class MovementBuilder {

    private final FirstMovement movement;

    public MovementBuilder() {
        movement = new FirstMovement();
    }

    public MovementBuilder from(Position startPosition) {
        movement.startPosition = startPosition;
        return this;
    }

    public MovementBuilder to(Position finalPosition) {
        movement.finalPosition = finalPosition;
        return this;
    }

    public MovementBuilder givenRoll(Dice dice) {
        movement.dice = dice;
        return this;
    }

    public FirstMovement end() {
        return movement;
    }
}