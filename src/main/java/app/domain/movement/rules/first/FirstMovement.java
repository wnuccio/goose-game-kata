package app.domain.movement.rules.first;

import app.domain.movement.MovementPresenter;
import app.domain.movement.PresentableMovement;
import app.domain.player.Position;

public class FirstMovement extends PresentableMovement {

    public FirstMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) { movementPresenter.presentFirstMovement(this); }
}