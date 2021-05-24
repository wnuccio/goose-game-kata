package app.domain.rules.goose;

import app.domain.movement.PlayerTurn;
import app.domain.movement.PresentableMovement;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

public class GooseMovement extends PresentableMovement {

    public GooseMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurn playerTurn) {
        new GoosePresenter().present(this.finalPosition(), presenter, playerTurn);
    }
}