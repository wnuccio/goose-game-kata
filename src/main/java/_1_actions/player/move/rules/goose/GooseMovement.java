package _1_actions.player.move.rules.goose;

import _2_domain.player.Position;
import _2_domain.presenter.MovementPresenter;
import _2_domain.presenter.PresentableMovement;

public class GooseMovement extends PresentableMovement {

    public GooseMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentGooseMovement(this);
    }
}