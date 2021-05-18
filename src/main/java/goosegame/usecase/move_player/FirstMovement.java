package goosegame.usecase.move_player;

import goosegame.domain.Position;

public class FirstMovement implements Movement {
    private final Position startPosition;
    private final Position finalPosition;

    public FirstMovement(Position startPosition, Position finalPosition) {
        this.startPosition = startPosition;
        this.finalPosition = finalPosition;
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