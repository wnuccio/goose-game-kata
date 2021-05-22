package _1_actions.player.move.rules.first;

import _2_domain.player.Position;
import _2_domain.presenter.MovementPresenter;
import _2_domain.presenter.PresentableMovement;

public class FirstMovement extends PresentableMovement {

    public FirstMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) { movementPresenter.presentFirstMovement(this); }
}