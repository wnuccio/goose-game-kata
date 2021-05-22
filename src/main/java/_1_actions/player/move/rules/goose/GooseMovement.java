package _1_actions.player.move.rules.goose;

import _2_domain.player.Position;
import _2_domain.presenter.PlayerTurnView;
import _2_domain.presenter.PresentableMovement;
import _2_domain.presenter.StringBuilderPresenter;

public class GooseMovement extends PresentableMovement {

    public GooseMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        new GoosePresenter().present(this.finalPosition(), presenter, playerTurnView);
    }
}