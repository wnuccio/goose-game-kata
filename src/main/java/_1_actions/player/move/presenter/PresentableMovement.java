package _1_actions.player.move.presenter;

import _2_domain.Movement;
import _2_domain.Position;


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
