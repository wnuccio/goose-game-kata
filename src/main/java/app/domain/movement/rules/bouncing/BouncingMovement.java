package app.domain.movement.rules.bouncing;

import app.domain.movement.MovementPresenter;
import app.domain.movement.PresentableMovement;
import app.domain.player.Position;

import static app.domain.player.Position.WIN;

public class BouncingMovement extends PresentableMovement {

    public BouncingMovement(Position finalPosition) { super(WIN, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentBouncing(this);
    }
}