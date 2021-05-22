package _1_actions.player.move.rules.bouncing;

import _2_domain.player.Position;
import _2_domain.presenter.MovementPresenter;
import _2_domain.presenter.PresentableMovement;

public class BouncingMovement extends PresentableMovement {

    public BouncingMovement(Position finalPosition) { super(Position.WIN, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentBouncing(this);
    }
}