package _1_actions.player.move.rules.first;

import _1_actions.player.move.presenter.MovementPresenter;
import _1_actions.player.move.presenter.PresentableMovement;
import _2_domain.Position;

public class FirstMovement extends PresentableMovement {

    public FirstMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) { movementPresenter.presentFirstMovement(this); }
}