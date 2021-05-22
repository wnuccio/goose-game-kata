package _1_actions.player.move.rules.bridge;

import _2_domain.player.Position;
import _2_domain.presenter.PlayerTurnView;
import _2_domain.presenter.PresentableMovement;
import _2_domain.presenter.StringBuilderPresenter;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement() { super(Position.BRIDGE, Position.BRIDGE_TARGET); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        new JumpOnBridgePresenter().present(presenter, playerTurnView);
    }
}