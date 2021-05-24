package app.domain.rules.bridge;

import app.domain.movement.PlayerTurnView;
import app.domain.movement.PresentableMovement;
import app.domain.player.Board;
import app.domain.presenter.StringBuilderPresenter;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement(Board board) { super(board.bridge(), board.bridgeTarget()); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        new JumpOnBridgePresenter().present(presenter, playerTurnView);
    }
}