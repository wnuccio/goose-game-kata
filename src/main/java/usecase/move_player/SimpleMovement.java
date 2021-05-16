package usecase.move_player;

import domain.Dice;
import domain.Position;

public class SimpleMovement implements Movement {
    int finalPosition;
    int startPosition;
    Dice dice;

    public static MovementBuilder movement() {
        return new MovementBuilder();
    }

    @Override
    public Position startPosition() {
        return Position.of(startPosition);
    }

    @Override
    public Position finalPosition() {
        return Position.of(finalPosition);
    }

    @Override
    public boolean hasPreviousMovement() {
        return false;
    }

    @Override
    public Movement previousMovement() {
        throw new IllegalStateException();
    }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentSimpleMovement(this);
    }
}