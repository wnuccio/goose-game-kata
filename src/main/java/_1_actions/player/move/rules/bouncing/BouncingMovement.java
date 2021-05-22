package _1_actions.player.move.rules.bouncing;

import _2_domain.movement.PlayerTurnView;
import _2_domain.movement.PresentableMovement;
import _2_domain.player.Position;
import _2_domain.presenter.StringBuilderPresenter;

public class BouncingMovement extends PresentableMovement {

    public BouncingMovement(Position finalPosition) { super(Position.WIN, finalPosition); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        new BouncingPresenter().present(this.finalPosition(), presenter, playerTurnView);
    }
}