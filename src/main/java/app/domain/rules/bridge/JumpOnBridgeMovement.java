package app.domain.rules.bridge;

import app.domain.movement.PlayerOnTurn;
import app.domain.movement.PresentableMovement;
import app.domain.player.Board;
import app.domain.presenter.StringBuilderPresenter;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement(Board board) { super(board.position(6), board.position(12)); }

    @Override
    public void present(PlayerOnTurn playerOnTurn, StringBuilderPresenter presenter) {
        new JumpOnBridgePresenter().present(presenter, playerOnTurn);
    }
}