package _2_domain.rules.bridge;

import _2_domain.movement.PlayerTurnView;
import _2_domain.movement.PresentableMovement;
import _2_domain.player.Position;
import _2_domain.presenter.StringBuilderPresenter;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement() { super(Position.BRIDGE, Position.BRIDGE_TARGET); }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        new JumpOnBridgePresenter().present(presenter, playerTurnView);
    }
}