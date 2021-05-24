package app.domain.rules.bouncing;

import app.domain.movement.PlayerTurnView;
import app.domain.movement.PresentableMovement;
import app.domain.player.Board;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

public class BouncingMovement extends PresentableMovement {

    public BouncingMovement(Board board, Position finalPosition) { super(board.winPosition(), finalPosition); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        new BouncingPresenter().present(this.finalPosition(), presenter, playerTurnView);
    }
}