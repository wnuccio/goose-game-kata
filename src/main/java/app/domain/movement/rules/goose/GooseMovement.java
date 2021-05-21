package app.domain.movement.rules.goose;

import app.domain.movement.MovementPresenter;
import app.domain.movement.PresentableMovement;
import app.domain.player.Position;

public class GooseMovement extends PresentableMovement {

    public GooseMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentGooseMovement(this);
    }
}