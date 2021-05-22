package _2_domain.presenter;

import _2_domain.movement.Movement;
import _2_domain.player.Position;


public abstract class PresentableMovement implements Movement {
    private final Position startPosition;
    private final Position finalPosition;

    public PresentableMovement(Position startPosition, Position finalPosition) {
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

    public abstract void present(MovementPresenter movementPresenter);
}
