package _1_actions.player.move.rules.goose;

import _1_actions.player.move.presenter.MovementPresenter;
import _1_actions.player.move.presenter.PresentableMovement;
import _2_domain.Position;

public class GooseMovement extends PresentableMovement {

    public GooseMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentGooseMovement(this);
    }
}