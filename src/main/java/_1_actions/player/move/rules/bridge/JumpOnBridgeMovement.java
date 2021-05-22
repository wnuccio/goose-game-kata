package _1_actions.player.move.rules.bridge;

import _1_actions.player.move.presenter.MovementPresenter;
import _1_actions.player.move.presenter.PresentableMovement;
import _2_domain.Position;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement() { super(Position.BRIDGE, Position.BRIDGE_TARGET); }

    @Override
    public void present(MovementPresenter movementPresenter) { movementPresenter.presentJumpOnBridge(this); }
}