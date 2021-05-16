package usecase.move_player;

import domain.Dice;
import domain.Position;

public class SimpleMovement implements Movement {
    Position finalPosition;
    Position startPosition;
    Dice dice;

    public static MovementBuilder movement() {
        return new MovementBuilder();
    }

    @Override
    public Position startPosition() {
        return startPosition;
    }

    @Override
    public Position finalPosition() {
        return finalPosition;
    }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentSimpleMovement(this);
    }
}