package app.domain.rules.bridge;

import app.domain.movement.PlayerTurn;
import app.domain.movement.PresentableMovement;
import app.domain.player.Board;
import app.domain.presenter.StringBuilderPresenter;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement(Board board) { super(board.bridge(), board.bridgeTarget()); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurn playerTurn) {
        new JumpOnBridgePresenter().present(presenter, playerTurn);
    }
}