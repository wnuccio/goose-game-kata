package app.domain.rules.first;

import app.domain.movement.PresentableMovement;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

public class FirstMovement extends PresentableMovement {

    public FirstMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(PlayerOnTurn playerOnTurn, StringBuilderPresenter presenter) {
        new FirstMovementPresenter().present(this, presenter, playerOnTurn);
    }
}