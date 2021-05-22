package _1_actions.player.move.rules.bouncing;

import _1_actions.player.move.presenter.MovementPresenter;
import _1_actions.player.move.presenter.PresentableMovement;
import _2_domain.Position;

public class BouncingMovement extends PresentableMovement {

    public BouncingMovement(Position finalPosition) { super(Position.WIN, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentBouncing(this);
    }
}