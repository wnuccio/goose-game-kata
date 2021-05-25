package app.domain.rules.first;

import app.domain.movement.PlayerOnTurn;
import app.domain.movement.PresentableMovement;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

public class FirstMovement extends PresentableMovement {

    public FirstMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerOnTurn playerOnTurn) {
        new FirstMovementPresenter().present(this, presenter, playerOnTurn);
    }
}