package _2_domain.rules.bouncing;

import _2_domain.movement.PlayerTurnView;
import _2_domain.movement.PresentableMovement;
import _2_domain.player.Board;
import _2_domain.player.Position;
import _2_domain.presenter.StringBuilderPresenter;

public class BouncingMovement extends PresentableMovement {

    public BouncingMovement(Board board, Position finalPosition) { super(board.winPosition(), finalPosition); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        new BouncingPresenter().present(this.finalPosition(), presenter, playerTurnView);
    }
}