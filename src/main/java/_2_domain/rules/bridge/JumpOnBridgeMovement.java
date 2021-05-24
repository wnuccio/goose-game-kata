package _2_domain.rules.bridge;

import _2_domain.movement.PlayerTurnView;
import _2_domain.movement.PresentableMovement;
import _2_domain.player.Board;
import _2_domain.presenter.StringBuilderPresenter;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement(Board board) { super(board.bridge(), board.bridgeTarget()); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        new JumpOnBridgePresenter().present(presenter, playerTurnView);
    }
}