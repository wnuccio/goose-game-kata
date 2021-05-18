package goosegame.usecase.move_player;

import goosegame.domain.Dice;
import goosegame.domain.Position;

public class FirstMovement implements Movement {
    Position finalPosition;
    Position startPosition;
    Dice dice;

    public static MovementBuilder firstMovement() {
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
        movementPresenter.presentFirstMovement(this);
    }
}