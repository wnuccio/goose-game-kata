package _1_actions.player.move.rules.bridge;

import _2_domain.player.Position;
import _2_domain.presenter.MovementPresenter;
import _2_domain.presenter.PresentableMovement;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement() { super(Position.BRIDGE, Position.BRIDGE_TARGET); }

    @Override
    public void present(MovementPresenter movementPresenter) { movementPresenter.presentJumpOnBridge(this); }
}