package app.domain.rules.bouncing;

import app.domain.movement.PlayerOnTurn;
import app.domain.movement.PresentableMovement;
import app.domain.player.Board;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

public class BouncingMovement extends PresentableMovement {

    public BouncingMovement(Board board, Position finalPosition) { super(board.win(), finalPosition); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerOnTurn playerOnTurn) {
        new BouncingPresenter().present(this.finalPosition(), presenter, playerOnTurn);
    }
}